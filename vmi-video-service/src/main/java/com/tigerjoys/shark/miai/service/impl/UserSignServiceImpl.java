package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.ICommodityGroupContract;
import com.tigerjoys.shark.miai.inter.contract.ICommodityGroupRelationshipContract;
import com.tigerjoys.shark.miai.inter.contract.IUserSignCardContract;
import com.tigerjoys.shark.miai.inter.contract.IUserSignLogContract;
import com.tigerjoys.shark.miai.inter.entity.CommodityGroupEntity;
import com.tigerjoys.shark.miai.inter.entity.CommodityGroupRelationshipEntity;
import com.tigerjoys.shark.miai.inter.entity.UserSignCardEntity;
import com.tigerjoys.shark.miai.inter.entity.UserSignLogEntity;
import com.tigerjoys.shark.miai.service.IUserSignService;

/**
 * App升级服务实现类
 * @author lipeng
 *
 */
@Service
public class UserSignServiceImpl implements IUserSignService {
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private ICommodityGroupContract commodityGroupContract;
	
	@Autowired
	private ICommodityGroupRelationshipContract commodityGroupRelationshipContract;
	
	@Autowired
	private IUserSignLogContract userSignLogContract;
	
	@Autowired
	private IUserSignCardContract userSignCardContract;
	
	@Override
	public void initSign(Model model) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		//查看今天签没签到
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("sign_time", Tools.getDate()));
		List<UserSignLogEntity> sign = userSignLogContract.load(pageModel);
		if (Tools.isNotNull(sign)) {
			model.addAttribute("isSign", true);//是否签到
			
			List<UserSignLogEntity> signList = getSignList(userid);//这个月所有的签到数据
			
			if (Tools.getDay()==signList.size()) {
				model.addAttribute("signDays", signList.size());//连续签到天数
			}else{
				List<Integer> daylist = new ArrayList<Integer>();
				for (UserSignLogEntity userSignLogEntity : signList) {
					daylist.add(userSignLogEntity.getDay());
				}
				int signDays = 0;
				for (int i = Tools.getDay(); i >=1; i--) {
					if (daylist.contains(i)) {
						signDays++;
					}else{
						break;
					}
				}
				model.addAttribute("signDays", signDays);//连续签到天数
			}
			model.addAttribute("missDays", Tools.getDay()-signList.size());//漏签的天数
			model.addAttribute("Days", Tools.getCurrentMonthLastDay()-Tools.getDay());//还需要签到的天数
		}else{
			model.addAttribute("isSign", false);//是否签到
			if (Tools.getDay()>1) {//如果今天日期大于1查看昨天的
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("userid", userid));
				pageModel.addQuery(Restrictions.eq("sign_time", Tools.getDate(Tools.getDayTime(1))));
				sign = userSignLogContract.load(pageModel);
				List<UserSignLogEntity> signList = getSignList(userid);//这个月所有的签到数据
				if (Tools.isNotNull(sign)) {
					List<Integer> daylist = new ArrayList<Integer>();
					for (UserSignLogEntity userSignLogEntity : signList) {
						daylist.add(userSignLogEntity.getDay());
					}
					int signDays = 0;
					for (int i = Tools.getDay()-1; i >=1; i--) {
						if (daylist.contains(i)) {
							signDays++;
						}else{
							break;
						}
					}
					model.addAttribute("signDays", signDays);//连续签到天数
				}else{
					model.addAttribute("signDays", 0);//昨天没签连续签到天数就是0
				}
				int signCount = 0;//这个月签到的次数
				if (Tools.isNotNull(signList)) {
					signCount = signList.size();
				}
				model.addAttribute("missDays", Tools.getDay()-signCount-1);//漏签的天数
				model.addAttribute("Days", Tools.getCurrentMonthLastDay()-Tools.getDay()+1);//还需要签到的天数
			}else{
				model.addAttribute("signDays", 0);//连续签到天数
				model.addAttribute("missDays", 0);//漏签的天数
				model.addAttribute("Days", Tools.getCurrentMonthLastDay());//还需要签到的天数
			}
		}
	}

	@Override
	public JSONObject signing() throws Exception {
		JSONObject jsonObjectResult = new JSONObject();
		long userid = RequestUtils.getCurrent().getUserid();
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("sign_time", Tools.getDate()));
		List<UserSignLogEntity> signToday = userSignLogContract.load(pageModel);
		if (Tools.isNotNull(signToday)) {
			jsonObjectResult.put("success", false);
			jsonObjectResult.put("code", 1);
			jsonObjectResult.put("msg", "今天已经签过到");
			return jsonObjectResult;
		}
		try {
			UserSignLogEntity sign = new UserSignLogEntity();
			sign.setUserid(userid);
			sign.setCreate_time(new Date());
			sign.setSign_time(Tools.getDate());
			sign.setMonth(Tools.getMonth());
			sign.setDay(Tools.getDay());
			sign.setSign_status(0);
			userSignLogContract.insert(sign);
			
			//如果今天是最后一天
			if (Tools.getCurrentMonthLastDay()==Tools.getDay()) {
				if (isSignFull(userid)) {
					jsonObjectResult.put("signFull", true);
					//送豪礼
					signFullToSendGift(userid);
				}else{
					jsonObjectResult.put("signFull", false);
				}
			}else{
				jsonObjectResult.put("signFull", false);
			}
			
			int award = getMath();
			userIncomeAgent.changeIncomeAccount(userid, award, 1, UserIncomeAccountLogTypeEnum.sign_award, userid+""+sign.getId(), UserIncomeAccountLogTypeEnum.sign_award.getDesc());
			jsonObjectResult.put("award", (double)award/100);
			jsonObjectResult.put("success", true);
			jsonObjectResult.put("code", 0);
			jsonObjectResult.put("msg", "签到成功");
			return jsonObjectResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjectResult;
	}
	
	@Override
	public void supplement(Model model) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		if (userDiamondAgent.getDiamondBalance(userid)>Const.SIGN_CARD_PRICE) {
			model.addAttribute("canBuySignCard", true);
		}else{
			model.addAttribute("canBuySignCard", false);
		}
		UserSignCardEntity card = userSignCardContract.findByProperty("userid", userid);
		if (card != null) {
			model.addAttribute("signCard", card.getCard_count());
		}else{
			model.addAttribute("signCard", 0);
		}
	}
	
	
	@Override
	public JSONObject calendarInit() throws Exception {
		JSONObject jsonObjectResult = new JSONObject();
		long userid = RequestUtils.getCurrent().getUserid();
		
		JsonArray signList = new JsonArray();
		JsonArray notSignList = new JsonArray();
		JsonArray disableList = new JsonArray();
		JsonArray today = new JsonArray();
		
		List<Integer> daylist = new ArrayList<Integer>();
		List<UserSignLogEntity> signLogList = getSignList(userid);
		if (Tools.isNotNull(signLogList)) {
			for (UserSignLogEntity entity : signLogList) {
				daylist.add(entity.getDay());
			}
		}
		for (int i = 1; i <= Tools.getDay(); i++) {
			if (daylist.contains(i)) {
				JsonObject ob = new JsonObject();
				ob.addProperty("signDay", i);
				signList.add(ob);
			}else{
				JsonObject ob = new JsonObject();
				ob.addProperty("notSignDay", i);
				notSignList.add(ob);
			}
		}
		for (int i = Tools.getDay()+1; i <= Tools.getCurrentMonthLastDay(); i++) {
			JsonObject ob = new JsonObject();
			ob.addProperty("disableDay", i);
			disableList.add(ob);
		}
		JsonObject ob = new JsonObject();
		ob.addProperty("today", Tools.getDay());
		today.add(ob);
		jsonObjectResult.put("signList", signList.toString());
		jsonObjectResult.put("notSignList", notSignList.toString());
		jsonObjectResult.put("disableList", disableList.toString());
		jsonObjectResult.put("today", today.toString());
		jsonObjectResult.put("allDayInMonth", Tools.getCurrentMonthLastDay());
		return jsonObjectResult;
	}
	
	@Override
	public JSONObject buySignCard() throws Exception {
		JSONObject jsonObjectResult = new JSONObject();
		long userid = RequestUtils.getCurrent().getUserid();
		UserBO user = userAgent.findById(userid);
		if (user == null) {
			jsonObjectResult.put("success", false);
			jsonObjectResult.put("code", 1);
			jsonObjectResult.put("msg", "购买失败");
			return jsonObjectResult;
		}
		try {
			if (userDiamondAgent.getDiamondBalance(userid)>= Const.SIGN_CARD_PRICE) {
				UserSignCardEntity card = userSignCardContract.findByProperty("userid", userid);
				UserSignCardEntity signCard = new UserSignCardEntity();
				if (card!=null) {
					signCard.setId(card.getId());
					signCard.setCard_count(card.getCard_count()+1);
					userSignCardContract.update(signCard);
				}else{
					signCard.setCreate_time(new Date());
					signCard.setUpdate_time(new Date());
					signCard.setUserid(userid);
					signCard.setCard_count(1);
					userSignCardContract.insert(signCard);
				}
				//扣钻石
				userDiamondAgent.changeDiamondAccount(userid, Const.SIGN_CARD_PRICE, (long)0, UserDiamondAccountLogTypeEnum.pay_sign_card.getCode(), 0, null, userid+""+System.currentTimeMillis(), UserDiamondAccountLogTypeEnum.pay_sign_card.getDesc());
				jsonObjectResult.put("canBuySignCard", true);
				jsonObjectResult.put("success", true);
				jsonObjectResult.put("code", 0);
				jsonObjectResult.put("msg", "购买成功");
			}else{
				jsonObjectResult.put("success", false);
				jsonObjectResult.put("code", "5");
				jsonObjectResult.put("msg", "您的余额不够！");
				jsonObjectResult.put("canBuySignCard", false);
				return jsonObjectResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjectResult;
	}
	
	@Override
	public JSONObject suppleDays(String day) throws Exception {
		JSONObject jsonObjectResult = new JSONObject();
		long userid = RequestUtils.getCurrent().getUserid();
		UserSignCardEntity card = userSignCardContract.findByProperty("userid", userid);
		try {
			//如果补签的是今天 不扣 补签卡
			if (Tools.parseInt(day)==Tools.getDay()) {
				UserSignLogEntity sign = new UserSignLogEntity();
				sign.setUserid(userid);
				sign.setCreate_time(new Date());
				sign.setSign_time(Tools.getDate());
				sign.setMonth(Tools.getMonth());
				sign.setDay(Tools.getDay());
				sign.setSign_status(0);
				userSignLogContract.insert(sign);
				//如果今天是最后一天
				if (Tools.getCurrentMonthLastDay()==Tools.getDay()) {
					if (isSignFull(userid)) {
						jsonObjectResult.put("signFull", true);
						//给礼品 
						signFullToSendGift(userid);
					}else{
						jsonObjectResult.put("signFull", false);
					}
				}else{
					jsonObjectResult.put("signFull", false);
				}
				if (card == null) {
					jsonObjectResult.put("signCard", 0);
				}else{
					jsonObjectResult.put("signCard", card.getCard_count());
				}
				int award = getMath();
				//给奖励
				userIncomeAgent.changeIncomeAccount(userid, award, 1, UserIncomeAccountLogTypeEnum.sign_award, userid+""+sign.getId(), UserIncomeAccountLogTypeEnum.sign_award.getDesc());
				jsonObjectResult.put("award", (double)award/100);
				jsonObjectResult.put("success", true);
				jsonObjectResult.put("code", 0);
				jsonObjectResult.put("msg", "签到成功");
				return jsonObjectResult;
			}
			if (day == null) {
				jsonObjectResult.put("signCard", 0);
				jsonObjectResult.put("success", false);
				jsonObjectResult.put("code", 1);
				jsonObjectResult.put("msg", "数据错误！");
				return jsonObjectResult;
			}
			if (card == null) {
				jsonObjectResult.put("signCard", 0);
				jsonObjectResult.put("success", false);
				jsonObjectResult.put("code", 2);
				jsonObjectResult.put("msg", "您还没有购买补签卡！");
				return jsonObjectResult;
			}
			if (card.getCard_count()>0) {
				UserSignCardEntity signCard = new UserSignCardEntity();
				signCard.setId(card.getId());
				signCard.setCard_count(card.getCard_count()-1);
				userSignCardContract.update(signCard);
				String days = Tools.parseInt(day)<10?"0"+day:day;
				UserSignLogEntity sign = new UserSignLogEntity();
				sign.setUserid(userid);
				sign.setCreate_time(new Date());
				sign.setSign_time(Tools.getMonth()+"-"+days);
				sign.setDay(Tools.parseInt(day));
				sign.setMonth(Tools.getMonth());
				sign.setSign_status(1);
				userSignLogContract.insert(sign);
				//如果今天是最后一天
				if (Tools.getCurrentMonthLastDay()==Tools.getDay()){
					if (isSignFull(userid)) {
						jsonObjectResult.put("signFull", true);
						//给礼品
						//userIncomeAgent.changeIncomeAccount(userid, Const.SIGN_AWARD*100, 1, UserIncomeAccountLogTypeEnum.sign_award, userid+""+sign.getId(), UserIncomeAccountLogTypeEnum.sign_award.getDesc());
					}else{
						jsonObjectResult.put("signFull", false);
					}
				}else{
					jsonObjectResult.put("signFull", false);
				}
				int award = getMath();
				//给奖励
				userIncomeAgent.changeIncomeAccount(userid, award, 1, UserIncomeAccountLogTypeEnum.sign_award, userid+""+sign.getId(), UserIncomeAccountLogTypeEnum.sign_award.getDesc());
				jsonObjectResult.put("award", (double)award/100);
				jsonObjectResult.put("signCard", signCard.getCard_count());
				jsonObjectResult.put("success", true);
				jsonObjectResult.put("code", 0);
				jsonObjectResult.put("msg", "补签成功！");
			}else{
				jsonObjectResult.put("signCard", 0);
				jsonObjectResult.put("signFull", false);
				jsonObjectResult.put("success", false);
				jsonObjectResult.put("code", 0);
				jsonObjectResult.put("msg", "补签卡为0！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjectResult;
	}
	
	/**
	 * 获得本月签到列表
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<UserSignLogEntity> getSignList(long userid) throws Exception{
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("month", Tools.getMonth()));
		pageModel.addQuery(Restrictions.eq("userid", userid));
		List<UserSignLogEntity> signList = userSignLogContract.load(pageModel);
		return signList;
	}
	
	/**
	 * 判断本月是否签到完毕
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean isSignFull(long userid) throws Exception{
		List<UserSignLogEntity> signList = getSignList(userid);
		if(signList.size() == Tools.getCurrentMonthLastDay()){
			return true;
		}
		return false;
	}
	
	/**
	 * 签到满送礼品
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public void signFullToSendGift(long userid) throws Exception{
		try {
			PageModel pageModel = new PageModel();
			pageModel.addQuery(Restrictions.eq("low_value", 0));
			pageModel.addQuery(Restrictions.eq("high_value", 1));
			pageModel.addQuery(Restrictions.eq("type", 1));
			pageModel.addQuery(Restrictions.eq("status", 1));
			List<CommodityGroupEntity> grouplist = commodityGroupContract.load(pageModel);
			if (Tools.isNotNull(grouplist)) {
				CommodityGroupEntity groupEntity = grouplist.get(0);
				CommodityGroupRelationshipEntity relationshipEntity = new CommodityGroupRelationshipEntity();
				Date currDate = new Date();
				relationshipEntity.setCreate_time(currDate);
				relationshipEntity.setUpdate_time(currDate);
				relationshipEntity.setGroup_id(groupEntity.getId());
				relationshipEntity.setGroup_detail(groupEntity.getGroup_detail());
				relationshipEntity.setUserid(userid);
				relationshipEntity.setShow_status(0);
				relationshipEntity.setStatus(1);
				commodityGroupRelationshipContract.insert(relationshipEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 获得一个1——100的随机数
	 * @return
	 */
	public int getMath(){
		int i = (int)(Math.random()*100+1);
		return i;
	}

	public static void main(String[] args) {
		System.out.println(599/100);
	}
	
	
}

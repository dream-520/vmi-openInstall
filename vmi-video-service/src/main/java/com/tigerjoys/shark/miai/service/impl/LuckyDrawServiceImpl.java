package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shark.miai.common.enums.CouponGroupEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.ICouponAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserScvcAgent;
import com.tigerjoys.shark.miai.agent.IVipAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.ScvcAwardCategoryEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.enums.LunPanItemEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserLuckyDrawLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserLuckyDrawLogEntity;
import com.tigerjoys.shark.miai.service.ILuckyDrawService;

/**
 * App升级服务实现类
 * @author lipeng
 *
 */
@Service
public class LuckyDrawServiceImpl implements ILuckyDrawService {
	
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserLuckyDrawLogContract UserLuckyDrawLogContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private ICouponAgent couponAgent;
	
	@Autowired
	private IUserScvcAgent userScvcAgent;
	
	@Autowired
	private IVipAgent vipAgent;
	
	// 每次轮盘活动消费钻石数
	public static final int LUNPAN_PER_SPEND = 1;
	
	//轮盘活动项目集合
	private static List<LunPanItemEnum> lunPanItems;
	private static Map<String, List<Integer>> lunPanItemsMap;
	
	static {
		initAllLunPanItems();
	}
	
	/**
	 * 初始化轮盘项目
	 * 
	 * @param list
	 * @param map
	 * @param item
	 */
	private static void initLunPanItems(List<LunPanItemEnum> list, Map<String, List<Integer>> map, LunPanItemEnum item) {
		list.add(item);
		List<Integer> l = map.get(item.getValue() + "_" + item.getType());
		if (l == null) {
			l = new ArrayList<Integer>();
		}
		l.add(list.size() - 1);
		map.put(item.getValue() + "_" + item.getType(), l);
	} 
	
	private static void initAllLunPanItems() {
		lunPanItems = new ArrayList<LunPanItemEnum>();
		lunPanItemsMap = new HashMap<String, List<Integer>>();
		initLunPanItems(lunPanItems, lunPanItemsMap, LunPanItemEnum.experience_vip_7Days);
		initLunPanItems(lunPanItems, lunPanItemsMap, LunPanItemEnum.diamond_50);
		initLunPanItems(lunPanItems, lunPanItemsMap, LunPanItemEnum.diamond_5);
		initLunPanItems(lunPanItems, lunPanItemsMap, LunPanItemEnum.voucher_50);
		initLunPanItems(lunPanItems, lunPanItemsMap, LunPanItemEnum.SCVC_10);
		initLunPanItems(lunPanItems, lunPanItemsMap, LunPanItemEnum.xiexiecanyu);
	}
	
	@Override
	public void initLunPan(Model model) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		if (userDiamondAgent.getDiamondBalance(userid)>1) {
			model.addAttribute("canNext", true);
		}else{
			model.addAttribute("canNext", false);
		}
		model.addAttribute("items", lunPanItems);
	}

	@Override
	public JSONObject commitLunPan() throws Exception {
		JSONObject jsonObjectResult = new JSONObject();
		long userid = RequestUtils.getCurrent().getUserid();
		int score = 0;
		int award = 0;
		long luckyDrawId = 0;
		LunPanItemEnum result = LunPanItemEnum.xiexiecanyu;
		Map<String, List<Integer>> itemsMap = lunPanItemsMap;
		UserBO user = userAgent.findById(userid);
		if (user == null) {
			jsonObjectResult.put("success", false);
			jsonObjectResult.put("code", 1);
			jsonObjectResult.put("msg", "用户不存在");
			return jsonObjectResult;
		}
		try {
			
			if (userDiamondAgent.getDiamondBalance(userid)>=LUNPAN_PER_SPEND) {
				award = LUNPAN_PER_SPEND;
				result = getLunPanScore();
				score = result.getValue();
				
				//添加抽奖信息
				UserLuckyDrawLogEntity luckyDraw = new UserLuckyDrawLogEntity();
				luckyDraw.setUserid(userid);
				luckyDraw.setCreate_time(new Date());
				luckyDraw.setPrize_type(result.getType());
				luckyDraw.setReserved_int(result.getValue());
				luckyDraw.setRemark(result.getTitle());
				UserLuckyDrawLogContract.insert(luckyDraw);
				//扣除抽奖花费
				luckyDrawId = luckyDraw.getId();
				userDiamondAgent.changeDiamondAccount(userid, award, (long)0, UserDiamondAccountLogTypeEnum.pay_lucky_Draw.getCode(), 0, null, userid+""+luckyDrawId, UserDiamondAccountLogTypeEnum.pay_lucky_Draw.getDesc());
			}else{
				jsonObjectResult.put("success", false);
				jsonObjectResult.put("code", "5");
				jsonObjectResult.put("msg", "您的余额不够！");
				jsonObjectResult.put("canNext", false);
				return jsonObjectResult;
			}
			//发放奖励 TODO
			if (score > 0) {
				if (result.getType()==0) {//体验VIP7天
					vipAgent.giveAwayDay(userid, score, luckyDrawId+"");
					logger.info("抽奖获得"+result.getTitle());
				}else if (result.getType()==1) { //50钻石 or 5钻石
					userDiamondAgent.changeDiamondAccount(userid, score, (long)0, UserDiamondAccountLogTypeEnum.lucky_Draw_get.getCode(), 1, UserDiamondAccountLogTypeEnum.lucky_Draw_get.getCode(), userid+""+luckyDrawId, UserDiamondAccountLogTypeEnum.lucky_Draw_get.getDesc());
					logger.info("抽奖获得"+result.getTitle());
				}else if (result.getType()==2) { //50元代金券
					couponAgent.addUserCoupon(userid, CouponGroupEnum.lucky_draw.getGroup());
					logger.info("抽奖获得"+result.getTitle());
				}else if (result.getType()==3) { //SCVC10币
					userScvcAgent.changeScvcAccount(userid, score, 1, ScvcAwardCategoryEnum.LUCKY.getCode(), userid+""+luckyDrawId, ScvcAwardCategoryEnum.LUCKY.getTitle());
					logger.info("抽奖获得"+result.getTitle());
				}
			}
			// 下一次抽奖余额不够
			if (userDiamondAgent.getDiamondBalance(userid) < LUNPAN_PER_SPEND) {
				jsonObjectResult.put("canNext", false);
			} else {
				jsonObjectResult.put("canNext", true);
			}
			//中奖结果
			if (score > 0) {
				jsonObjectResult.put("text", "恭喜获得"+result.getTitle());
			}else{
				jsonObjectResult.put("text", result.getTitle());
			}
			jsonObjectResult.put("success", true);

			jsonObjectResult.put("index", getLunPanIndex(result, itemsMap));
			return jsonObjectResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjectResult;
	}
	
	/**
	 * 获取轮盘抽奖结果指针下标
	 * 
	 * @param score
	 * @param itemsMap
	 * @return
	 */
	private int getLunPanIndex(LunPanItemEnum result, Map<String, List<Integer>> itemsMap) {
		if (itemsMap != null) {
			List<Integer> list = itemsMap.get(result.getValue() + "_" + result.getType());
			if (list != null) {
				int size = list.size();
				if (size > 0) {
					// 如果轮盘上有多个相同的结果项目，随机取一个
					Random rand = new Random();
					return list.get(rand.nextInt(size));
				}
			}
		}
		return 0;
	}
	
	/**
	 * 轮盘奖品概率
	 * 
	 * @return
	 */
	private LunPanItemEnum getLunPanScore() {
		/**
		 * 1、体验VIP7天   3%概率
		 * 2、50钻       2%概率
		 * 3、5钻     5%概率
		 * 4、50元代金券    10%概率
		 * 5、SCVC10币   10%概率
		 * 6、谢谢参与     70%概率
		 **/
		Random rand = new Random();
		// i的区间为[0-10000)即，0到9999
		int i = rand.nextInt(10000);
		if (i < 300) {
			return LunPanItemEnum.experience_vip_7Days;
		} else if (i < 500) {
			return LunPanItemEnum.diamond_50;
		} else if (i < 1000) {
			return LunPanItemEnum.diamond_5;
		} else if (i < 2000) {
			return LunPanItemEnum.voucher_50;
		} else if (i < 3000) {
			return LunPanItemEnum.SCVC_10;
		} 
		return LunPanItemEnum.xiexiecanyu;
	}
	
}

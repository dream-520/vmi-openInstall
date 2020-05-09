package com.tigerjoys.shark.miai.pay;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.shark.miai.common.enums.AppNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IGlobalBroadcastAgent;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IProxyAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.enums.FirstPayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.GlobalBroadcastTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.RechargeOrderDonorTimesEnum;
import com.tigerjoys.shark.miai.agent.enums.RechargeOrderDonorTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.agent.enums.UserPointAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.dto.service.PushRichUserDto;
import com.tigerjoys.shark.miai.dto.service.UserBaseInfo;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.ICopyUserPayActionContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IUserFirstRechargeLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserNoWithdrawalBoundsContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.UserFirstRechargeLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.service.IVChatVideoYXService;

/** 
  * @author mouzhanpeng at [2017年10月11日 下午5:07:18] 
  * @since JDK 1.8.0 
  */
@Service(PayTypeEnum.RECHARGE)
public class RechargeDiamondPaid extends IPayAgent.NotifyCallback{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;

	@Autowired
	private IRechargeOrderContract rechargeOrderContract;
	
	@Autowired
	private IUserPointAgent userPointAgent;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IGlobalBroadcastAgent globalBroadcastAgent;

	@Autowired
	private IUserInviteContract userInviteContract;

	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	@Autowired
	private INewPushAgent newPushAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IVChatVideoYXService vChatVideoYXService;
	
	@Autowired
	private IUserFirstRechargeLogContract userFirstRechargeLogContract;
	
	@Autowired
	private IUserNoWithdrawalBoundsContract userNoWithdrawalBoundsContract;
	
	@Autowired
	private IProxyAgent proxyAgent;
	
	/**
	 * 充钻回调处理
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	protected void dealNotifyData(UserPayActionEntity entity) throws Exception {
		RechargeOrderEntity order = rechargeOrderContract.findById(entity.getOrder_id());
		// 扣收益
		if(0 < order.getIncome()){
			userIncomeAgent.changeIncomeAccount(order.getUser_id(), order.getIncome(), 0, UserIncomeAccountLogTypeEnum.buy_diamond, String.valueOf(order.getOrder_id()),
					UserIncomeAccountLogTypeEnum.buy_diamond.getDesc());
			userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 0, order.getIncome());
		}
		long chargeDiamond = order.getDiamond();
		if(order.getDonor_type() == RechargeOrderDonorTypeEnum.diamond.getCode()){
			if(order.getDonor() > 0){
			if(order.getDonor_times() == RechargeOrderDonorTimesEnum.first.getCode() && checkFirstRecharge(order.getUser_id())){
				chargeDiamond = order.getDiamond() + order.getDonor();
			}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.every.getCode()){
				chargeDiamond = order.getDiamond() + order.getDonor();
			}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.nothing.getCode()){
				chargeDiamond = order.getDiamond();
			}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.repetition_desc.getCode() && !checkFirstRecharge(order.getUser_id())){
				chargeDiamond = order.getDiamond() + order.getDonor();
			}
			}
		}
		// 充钻石
		if (Const.IOS_TEST_CHARGE_DIAMOND.containsKey(order.getUser_id())) {
			chargeDiamond = order.getPrice()/100;
		}
		
		userDiamondAgent.changeDiamondAccount(entity.getUser_id(), chargeDiamond,
				entity.getMoney(), UserDiamondAccountLogTypeEnum.recharge_account.getCode(), 1, entity.getPay_channel(),
				String.valueOf(order.getOrder_id()), entity.getDescription());
		//送积分
		if (chargeDiamond>=100) {
			userPointAgent.changePointAccount(entity.getUser_id(), UserPointAccountLogTypeEnum.recharge_limit_award.getCode(), 1, String.valueOf(entity.getId()), UserPointAccountLogTypeEnum.recharge_limit_award.getDesc());
		}
		//把充值记录添加到全局广播
		if (chargeDiamond>0) {
			globalBroadcastAgent.insert(entity.getUser_id(), 0, entity.getInitial_price()/100, GlobalBroadcastTypeEnum.recharge.getCode(), 0);
		}
		
		/**
		 * 充钻返收益
		 */
		if(order.getDonor_type() == RechargeOrderDonorTypeEnum.money.getCode()){
			if(order.getDonor() > 0){
				if(order.getDonor_times() == RechargeOrderDonorTimesEnum.first.getCode() && checkFirstRecharge(order.getUser_id())){
					userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 1, order.getDonor()*100);
					userIncomeAgent.changeIncomeAccount(entity.getUser_id(), order.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(order.getOrder_id()),
							UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
					
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.every.getCode()){
					userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 1, order.getDonor()*100);
					userIncomeAgent.changeIncomeAccount(entity.getUser_id(), order.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(order.getOrder_id()),
							UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.nothing.getCode()){
					
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.repetition_desc.getCode() && !checkFirstRecharge(order.getUser_id())){
					userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 1, order.getDonor()*100);
					userIncomeAgent.changeIncomeAccount(entity.getUser_id(), order.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(order.getOrder_id()),
							UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
				}
			}
		}
		
		try{
			vChatVideoYXService.closeObscurationClient(entity.getUser_id());
		}catch (Exception e) {
			logger.info("recharging-diamond closeObscuration error!", e);
		}
		
		//优先检测首次充值
		/*
		try {
			boolean first = userFirstRechargeLogContract.checkFirstRecharge(order.getUser_id(), FirstPayTypeEnum.all.getCode());
			if(first && order.getMoney().intValue() >= 5800) {
				//首次充值且满足大于等于58元
				AppStartSendMsgEntity t = new AppStartSendMsgEntity();
				if(Const.IS_TEST) {
					t.setFromId(131879189602173184L);
				} else {
					t.setFromId(65418719477628672L);
				}
				t.setToId(order.getUser_id());
				t.setMsg("您好，恭喜您获得"+ order.getMoney() / 100 +"元超值大礼机会，您可以联系微信客服chongzhikefu123456进行咨询");
				t.setSend_time(new Date().getTime() + 3000);
				appStartSendMsgContract.insert(t);
			}
		} catch (Exception e) {
			logger.warn("recharging-diamond send msg error!", e);
		}
		*/
		try {
			if (checkFirstRecharge(order.getUser_id())) {
				UserFirstRechargeLogEntity firstRechargeLog = new UserFirstRechargeLogEntity();
				firstRechargeLog.setUserid(order.getUser_id());
				firstRechargeLog.setCreate_time(new Date());
				firstRechargeLog.setType(FirstPayTypeEnum.diamond.getCode());
				userFirstRechargeLogContract.insert(firstRechargeLog);
			}
		} catch (Exception e) {
			logger.warn("recharging-diamond adds UserFirstRechargeLog error!", e);
		}
		
		
		/*
		if (order.getPackage_name().equals("com.tjhj.miliao")) {
			//赠送补签卡
			if (order.getDiamond()>=100) {
				try {
					UserSignCardEntity card = userSignCardContract.findByProperty("userid", entity.getUser_id());
					UserSignCardEntity signCard = new UserSignCardEntity();
					if (card == null) {
						signCard.setCard_count(order.getDiamond()/100);
						signCard.setCreate_time(new Date());
						signCard.setUpdate_time(new Date());
						signCard.setUserid(entity.getUser_id());
						userSignCardContract.insert(signCard);
					}else{
						signCard.setId(card.getId());
						signCard.setCard_count(card.getCard_count()+order.getMoney()/10000);
						userSignCardContract.update(signCard);
					}
				} catch (Exception e) {
					logger.warn("recharging-diamond adds sign-card error!", e);
				}
			}
			*/
			//送豪礼
		/*
			try {
				PageModel pageModel = new PageModel();
				pageModel.addQuery(Restrictions.le("low_value", order.getMoney()/100));
				pageModel.addQuery(Restrictions.ge("high_value", order.getMoney()/100));
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
					relationshipEntity.setUserid(entity.getUser_id());
					relationshipEntity.setShow_status(0);
					relationshipEntity.setStatus(1);
					commodityGroupRelationshipContract.insert(relationshipEntity);
				}
			} catch (Exception e) {
				logger.warn("recharging-diamond adds CommodityGroupRelationship error!", e);
			}
		}
		*/
	
	
		
		
		// 分成给邀请者
		try{
			if(AppNameEnum.andriod_com_tjhj_miliao.getPackageName().equalsIgnoreCase(order.getPackage_name())){
				proxyAgent.addCpsIncome(order.getUser_id(), order.getMoney());
			}else{
				UserInviteEntity inviter = userInviteContract.findByProperty("userid", order.getUser_id());
				if (Tools.isNotNull(inviter)) {
					/*
					PageModel pageModel = new PageModel();
					pageModel.addQuery(Restrictions.eq("user_id", order.getUser_id()));
					pageModel.addQuery(Restrictions.eq("status",1));
					rechargeOrderContract.count(pageModel) == 0
					*/
					if(inviter.getInvitation()>0){
						boolean anchorStateFlag = true;
						AnchorOnlineEntity anchorOnline = anchorOnlineContract.getAnchorOnlineEntity(inviter.getInvitation());
						if(Tools.isNotNull(anchorOnline)){
							if(anchorOnline.getState()!=1){
								anchorStateFlag = false;
							}
						}
						if(checkGiveIncome(order.getUser_id()) && anchorStateFlag){
							
							VacuateConfigDto vacuate = sysConfigAgent.vacuate();
							int income = Math.round(order.getMoney() * vacuate.getRechargeIncome() / 100);
							userIncomeAgent.changeIncomeAccount(inviter.getInvitation(), income, income, 1, UserIncomeAccountLogTypeEnum.recharge_back_invitation_income,
									String.valueOf(order.getOrder_id()), vacuate, UserIncomeAccountLogTypeEnum.recharge_back_invitation_income.getDesc());
						}
					}
					
				}
			}	
		}catch(Exception e){
			logger.warn("recharging-diamond adds UserInvite error!", e);
		}
		order.setStatus(1);
		order.setUpdate_time(new Date());
		rechargeOrderContract.update(order);
		// 充值送scvc
		/*
		try {
			userScvcAgent.gainScvcVia(entity.getUser_id(), ScvcAwardCategoryEnum.RECHARGE, String.valueOf(entity.getOrder_id()), ScvcAwardCategoryEnum.RECHARGE.getTitle());
		} catch (Exception e) {
			logger.warn("recharging-diamond adds scvc error!", e);
		}
		*/
		
		//充值滚动消息数据入库处理
		/*
		try {
			topHeadInfoContract.addTopHeadInfo(entity.getNickname(), "充值了" + entity.getMoney() / 100 + "元");
		} catch (Exception e) {
			logger.warn("recharging-diamond adds top tip error!", e);
		}
		*/
		
		//用户充值发送通知栏给在线的主播
		try {
			int d = order.getDiamond() + order.getDonor();
			UserBO user = userAgent.findById(entity.getUser_id());
			if(Tools.isNotNull(user)) {
				//寻找合适的主播进行发送通知栏
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("state", 1));
				pageModel.addQuery(Restrictions.eq("online", 3));
				pageModel.addQuery(Restrictions.eq("video_type", 1));
				pageModel.addQuery(Restrictions.sql("flag=0 order by rand() limit 1"));
				List<AnchorOnlineEntity> list = anchorOnlineContract.load(pageModel);
				if(Tools.isNotNull(list) && list.size() > 0) {
					AnchorOnlineEntity anchor = list.get(0);
					/*
					//测试账号
					AnchorOnlineEntity anchor = null;
					if(Const.IS_TEST) {
						anchor = anchorOnlineContract.findByProperty("userid", 66138512797270272L);
					} else {
						anchor = anchorOnlineContract.findByProperty("userid", 87152674235023616L);
					}
					*/

					if(Tools.isNotNull(anchor)) {
						UserBO anc = userAgent.findById(anchor.getUserid());
						long diamond = userDiamondAgent.getDiamondBalance(entity.getUser_id());
						PushParamDto paramC = PushHelper.getPushParamDto(anc, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.recharge_user);
						paramC.setTitle(user.getNickname()+"充值了"+d+"钻");
						paramC.setContent("来和他聊聊呗");
						//设置主播的id值
						paramC.setUserId(anc.getUserid());
						PushRichUserDto dto = new PushRichUserDto();
						UserBaseInfo otherUserData = new UserBaseInfo();
						otherUserData.setNickName(user.getNickname());
						otherUserData.setBalance("余额: "+ diamond);
						otherUserData.setUserId(user.getUserid());
						otherUserData.setPhoto(Const.getCdn(user.getPhoto()));
						dto.setOtherUserData(otherUserData);
						dto.setInfo("多聊多赚钱");
						paramC.setExtend(JsonHelper.toJson(dto));
						newPushAgent.pushMessageToSingleUser(paramC);
					}
				}
			}
		} catch (Exception e) {
			logger.warn("recharging-diamond send push message error!", e);
		}
	}
	
	
	private boolean checkFirstRecharge(long userid) throws Exception{
	
		return userFirstRechargeLogContract.checkFirstRecharge(userid, FirstPayTypeEnum.diamond.getCode());
	}
	
	private boolean checkGiveIncome(long userid) throws Exception{
		UserBO userBo = userAgent.findById(userid);
		LocalDate localDate1 = LocalDate.now();
		Instant instant = userBo.getCreateTime().toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate2 = localDateTime.toLocalDate();
		long days = localDate1.toEpochDay() - localDate2.toEpochDay();
		Random random = new Random();
		int rdm = random.nextInt(100);
		if(days<3){
			return true;
		}else if(days>=3 && days<10){
			if(rdm<80){
				return true;
			}
		}else if(days>=10 && days<20){
			if(rdm<70){
				return true;
			}
		}else if(days>=20 && days<30){
			if(rdm<60){
				return true;
			}
		}else if(days>=30){
			if(rdm<50){
				return true;
			}
		}
		return false;
		
	}
	
	
}

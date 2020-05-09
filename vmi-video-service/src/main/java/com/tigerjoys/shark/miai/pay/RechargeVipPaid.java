package com.tigerjoys.shark.miai.pay;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserPayVipSendMsgAgent;
import com.tigerjoys.shark.miai.agent.IUserTariffeAgent;
import com.tigerjoys.shark.miai.agent.IUserVipAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.agent.enums.UserTariffeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.service.IVchatTcpMessageService;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IBSequenceContract;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserNoWithdrawalBoundsContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;

/** 
  * @author mouzhanpeng at [2017年10月11日 下午5:07:18] 
  * @since JDK 1.8.0 
  */
@Service(PayTypeEnum.VIP)
public class RechargeVipPaid extends IPayAgent.NotifyCallback{

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IUserNoWithdrawalBoundsContract userNoWithdrawalBoundsContract;

	@Autowired
	private IGuardVipOrderContract guardVipOrderContract;
	
	@Autowired
	private IGuardVipCategoryContract guardVipCategoryContract;
	
	@Autowired
	private IUserVipAgent userVipAgent;
	
	@Autowired
	private IUserTariffeAgent userTariffeAgent;
	
	@Autowired
	private IUserPayVipSendMsgAgent userPayVipSendMsgAgent;
	
	@Autowired
	private IVchatTcpMessageService vchatTcpMessageService;
	
	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private IBSequenceContract bSequenceContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserInviteContract userInviteContract;
	
	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	
	
	
	 
	/**
	 * 购买VIP回调处理
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	protected void dealNotifyData(UserPayActionEntity entity) throws Exception {
		GuardVipOrderEntity order = guardVipOrderContract.findById(entity.getOrder_id());
		// 扣收益
		if(0 < order.getIncome()){
			userIncomeAgent.changeIncomeAccount(order.getUser_id(), order.getIncome(), 0, UserIncomeAccountLogTypeEnum.income_buy_vip, String.valueOf(order.getOrder_id()),
					UserIncomeAccountLogTypeEnum.income_buy_vip.getDesc());
			userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 0, order.getIncome());
		}
		UserBO bo = userAgent.findById(order.getUser_id());
		//检测是否需要触发对应的发送联系方式的任务
		try {
			//只对android的发送消息
			if(Tools.isNotNull(bo) && bo.getPlatform() == 1) {
				userPayVipSendMsgAgent.userFirstPayVip(order.getUser_id());
			}
		} catch (Exception e) {
			logger.warn("send contact msg error!", e);
		}
		
		userVipAgent.increaseVipDay(order.getUser_id(), order.getDays());
		
		GuardVipCategoryEntity categoryPrice = guardVipCategoryContract.findById(order.getPrice_id());
		if (Tools.isNotNull(categoryPrice)) {
			if (categoryPrice.getBuy_num() > 0) {
				userTariffeAgent.changeTariffeAccount(order.getUser_id(), categoryPrice.getBuy_num()*100, UserTariffeAccountLogTypeEnum.vip_give.getCode(), 1, entity.getOrder_id()+"",UserTariffeAccountLogTypeEnum.vip_give.getDesc());
			}
			
			if (categoryPrice.getDiamond()> 0) {
				userDiamondAgent.changeDiamondAccount(entity.getUser_id(), categoryPrice.getDiamond(),
						entity.getMoney(), UserDiamondAccountLogTypeEnum.buy_vip_give_diamond.getCode(), 1, null,
						String.valueOf(order.getOrder_id()), entity.getDescription());
			}
		}
		
		
		vchatTcpMessageService.sendShortVideoOpenStatus(order.getUser_id(), 1);
		
		try{
			bSequenceContract.getCurrentValue("vipBuy", 1L);
		}catch (Exception e) {
			logger.warn("vip购买自增次数失败", e);
		}
		
		
		// 送小红花
		/*
		if(order.getDonor()>0){
			redFlowerAgent.changeRedFlowerAccount(entity.getUser_id(), order.getDonor(),
					entity.getMoney(), RedFlowerAccountLogTypeEnum.recharge_vip_give_redflower.getCode(), 1, entity.getPay_channel(),
					String.valueOf(order.getOrder_id()), entity.getDescription());
		}
		*/
		/*
		//送积分
		if (entity.getMoney()/100>=100) {
			userPointAgent.changePointAccount(entity.getUser_id(), UserPointAccountLogTypeEnum.recharge_limit_award.getCode(), 1, String.valueOf(entity.getId()), UserPointAccountLogTypeEnum.recharge_limit_award.getDesc());
		}
		//把充值记录添加到全局广播
		if (entity.getMoney()>0) {
			globalBroadcastAgent.insert(entity.getUser_id(), 0, entity.getMoney()/100>=58?entity.getMoney()/100:58, GlobalBroadcastTypeEnum.recharge.getCode(), 0);
		}
		*/
	
		
		// 分成给邀请者
		try{
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
						userIncomeAgent.changeIncomeAccount(inviter.getInvitation(), income, income, 1, UserIncomeAccountLogTypeEnum.recharge_vip_back_invitation_income,
								String.valueOf(order.getOrder_id()), vacuate, UserIncomeAccountLogTypeEnum.recharge_vip_back_invitation_income.getDesc());
					}
				}
				
			}
		}catch (Exception e) {
			logger.info("购买VIP送邀请者收益",e);
		}
		
		//优先检测首次充值
		/*
		try {
			boolean first = userFirstRechargeLogContract.checkFirstRecharge(order.getUser_id(), FirstPayTypeEnum.all.getCode());
			
			if(first) {
				UserFirstRechargeLogEntity firstRechargeLog = new UserFirstRechargeLogEntity();
				firstRechargeLog.setUserid(order.getUser_id());
				firstRechargeLog.setCreate_time(new Date());
				firstRechargeLog.setType(FirstPayTypeEnum.flower.getCode());
				userFirstRechargeLogContract.insert(firstRechargeLog);
			}
		} catch (Exception e) {
			logger.warn("recharging-diamond send msg error!", e);
		}
		*/
		order.setStatus(1);
		order.setUpdate_time(new Date());
		guardVipOrderContract.update(order);
		
		try {
			//只对android的发送消息
			if(Tools.isNotNull(bo) && bo.getPlatform() == 1) {
				ExecutorServiceHelper.executor.execute(new PushMessageThread(order.getUser_id()));
			}
		} catch (Exception e) {
			
		}
	}

	private class PushMessageThread implements Runnable {

		private long userid;
		
		public PushMessageThread(long userid) {
			this.userid = userid;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(200);
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("state", 1));
				pageModel.addQuery(Restrictions.eq("flag", 0));
				List<AnchorOnlineEntity> list = anchorOnlineContract.load(pageModel);
				if(Tools.isNotNull(list) && list.size() > 0) {
					for (AnchorOnlineEntity anchor : list) {
						neteaseAgent.pushOneMessageNORoam(userid, anchor.getUserid(), "我已经是VIP特色会员了，找我聊天吧~", false);
						Thread.sleep(10);
					}
				}
			} catch (Exception e) {
				logger.error("给全部主播发送消息出现问题:"+userid);
			}
		}
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

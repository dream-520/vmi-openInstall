package com.tigerjoys.shark.miai.pay;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.shark.miai.agent.IAnchorDefendAgent;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IUserNoWithdrawalBoundsContract;
import com.tigerjoys.shark.miai.inter.entity.GuardVipOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;

/** 
  * @author mouzhanpeng at [2017年10月11日 下午5:07:18] 
  * @since JDK 1.8.0 
  */
@Service(PayTypeEnum.GUARD)
public class RechargeGuardPaid extends IPayAgent.NotifyCallback{

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IUserNoWithdrawalBoundsContract userNoWithdrawalBoundsContract;

	@Autowired
	private IGuardVipOrderContract guardVipOrderContract;
	
	@Autowired
	private IAnchorDefendAgent anchorDefendAgent;
	
	/**
	 * 购买守护回调处理
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	protected void dealNotifyData(UserPayActionEntity entity) throws Exception {
		GuardVipOrderEntity order = guardVipOrderContract.findById(entity.getOrder_id());
		// 扣收益
		if(0 < order.getIncome()){
			userIncomeAgent.changeIncomeAccount(order.getUser_id(), order.getIncome(), 0, UserIncomeAccountLogTypeEnum.income_buy_guard, String.valueOf(order.getOrder_id()),
					UserIncomeAccountLogTypeEnum.income_buy_guard.getDesc());
			userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 0, order.getIncome());
		}
		long anchorIncome = Math.round(order.getMoney().intValue()*0.4);
		userIncomeAgent.changeIncomeAccount(order.getAnchorId(), anchorIncome, 1, UserIncomeAccountLogTypeEnum.buy_guard_give_anchor_income, String.valueOf(order.getOrder_id()),
				UserIncomeAccountLogTypeEnum.buy_guard_give_anchor_income.getDesc());
		
		anchorDefendAgent.buyAnchorDefend(entity.getOrder_id());
		
	
		
		
		// 送小红花
		/*
		if(order.getDonor()>0){
			redFlowerAgent.changeRedFlowerAccount(entity.getUser_id(), order.getDonor(),
					entity.getMoney(), RedFlowerAccountLogTypeEnum.recharge_guard_give_redflower.getCode(), 1, entity.getPay_channel(),
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
		
		/*
		
		UserInviteEntity inviter = userInviteContract.findByProperty("userid", order.getUser_id());
		if (Tools.isNotNull(inviter)) {
			PageModel pageModel = new PageModel();
			pageModel.addQuery(Restrictions.eq("user_id", order.getUser_id()));
			pageModel.addQuery(Restrictions.eq("status",1));
			if(rechargeOrderContract.count(pageModel) == 0 ){
				VacuateConfigDto vacuate = sysConfigAgent.vacuate();
				int income = Math.round(order.getPrice() * vacuate.getRechargeIncome() / 100);
				userIncomeAgent.changeIncomeAccount(inviter.getInvitation(), income, income, 1, UserIncomeAccountLogTypeEnum.recharge_guard_back_invitation_income,
						String.valueOf(order.getOrder_id()), vacuate, UserIncomeAccountLogTypeEnum.recharge_guard_back_invitation_income.getDesc());
			}
		}
		*/
		
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
	
	}

	
}

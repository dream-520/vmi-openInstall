package com.tigerjoys.shark.miai.pay;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.shark.miai.agent.IFreeVideoChatExperienceAgent;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.inter.contract.IBuyDialExperienceOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IUserNoWithdrawalBoundsContract;
import com.tigerjoys.shark.miai.inter.entity.BuyDialExperienceOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;

/** 
  * @author yangjunming
  * @since JDK 1.8.0 
  */
/**
 * 购习拨打体验机会
 * @author Administrator
 *
 */
@Service(PayTypeEnum.DIAL_EXPERIENCE)
public class RechargeDialExperiencePaid extends IPayAgent.NotifyCallback{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	

	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	
	@Autowired
	private IUserNoWithdrawalBoundsContract userNoWithdrawalBoundsContract;
	
	
	@Autowired
	private IBuyDialExperienceOrderContract buyDialExperienceOrderContract;
	
	
	@Autowired
	private IFreeVideoChatExperienceAgent freeVideoChatExperienceAgent;
	 
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	protected void dealNotifyData(UserPayActionEntity entity) throws Exception {
		BuyDialExperienceOrderEntity order = buyDialExperienceOrderContract.findById(entity.getOrder_id());
		// 扣收益
		if(0 < order.getIncome()){
			userIncomeAgent.changeIncomeAccount(order.getUser_id(), order.getIncome(), 0, UserIncomeAccountLogTypeEnum.income_buy_dial_experience, String.valueOf(order.getOrder_id()),
					UserIncomeAccountLogTypeEnum.income_buy_dial_experience.getDesc());
			userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 0, order.getIncome());
		}
		
		freeVideoChatExperienceAgent.insertRecord(order.getUser_id(),"",0,1);
		
		order.setStatus(1);
		order.setUpdate_time(new Date());
		buyDialExperienceOrderContract.update(order);
		logger.info("用户ID:"+order.getUser_id()+"购买");
	
	}

	
}

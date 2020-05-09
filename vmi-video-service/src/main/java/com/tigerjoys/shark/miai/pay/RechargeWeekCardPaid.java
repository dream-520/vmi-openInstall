package com.tigerjoys.shark.miai.pay;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserWeekCardAgent;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.RedFlowerAccountLogTypeEnum;
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
@Service(PayTypeEnum.ROOM_WEEKS_CARD)
public class RechargeWeekCardPaid extends IPayAgent.NotifyCallback{

	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IUserWeekCardAgent userWeekCardAgent;
	
	@Autowired
	private IRedFlowerAgent redFlowerAgent;
	
	@Autowired
	private IUserNoWithdrawalBoundsContract userNoWithdrawalBoundsContract;

	@Autowired
	private IGuardVipOrderContract guardVipOrderContract;
	
	 
	
	/**
	 * 购买周卡回调处理
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	protected void dealNotifyData(UserPayActionEntity entity) throws Exception {
		GuardVipOrderEntity order = guardVipOrderContract.findById(entity.getOrder_id());
		// 扣收益
		if (0 < order.getIncome()) {
			userIncomeAgent.changeIncomeAccount(order.getUser_id(), order.getIncome(), 0,
					UserIncomeAccountLogTypeEnum.income_buy_week_card, String.valueOf(order.getOrder_id()),
					UserIncomeAccountLogTypeEnum.income_buy_week_card.getDesc());
			userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 0,
					order.getIncome());
		}

		// 送小红花
		if (order.getDonor() > 0) {
			redFlowerAgent.changeRedFlowerAccount(entity.getUser_id(), order.getDonor(), entity.getMoney(),
					RedFlowerAccountLogTypeEnum.recharge_week_card_give_redflower.getCode(), 1, entity.getPay_channel(),
					String.valueOf(order.getOrder_id()), entity.getDescription());
		}

		userWeekCardAgent.changeWeekCardAccount(entity.getUser_id(), order.getPrice_id(), entity.getOrder_id() + "",
				order.getType() == 4 ? "绿钻周卡" : "黄钻周卡");

		order.setStatus(1);
		order.setUpdate_time(new Date());
		guardVipOrderContract.update(order);

	}
	
}

package com.tigerjoys.shark.miai.pay;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserEnergyAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserEnergyAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IEnergyOrderContract;
import com.tigerjoys.shark.miai.inter.contract.ITopHeadInfoContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.entity.EnergyOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;

/** 
  * @author mouzhanpeng at [2017年10月11日 下午5:07:18] 
  * @since JDK 1.8.0 
  */
@Service(PayTypeEnum.ENERGY)
public class RechargeEnergyPaid extends IPayAgent.NotifyCallback{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserEnergyAgent userEnergyAgent;

	@Autowired
	private IEnergyOrderContract energyOrderContract;
	
	@Autowired
	private ITopHeadInfoContract topHeadInfoContract;

	@Autowired
	private IUserIncomeAgent userIncomeAgent;

	@Autowired
	private IUserInviteContract userInviteContract;

	@Autowired
	private ISysConfigAgent sysConfigAgent;

	@Override
	protected void dealNotifyData(UserPayActionEntity entity) throws Exception {
		EnergyOrderEntity order = energyOrderContract.findById(entity.getOrder_id());
		if(0 < order.getIncome()){
			userIncomeAgent.changeIncomeAccount(order.getUser_id(), order.getIncome(), 0, UserIncomeAccountLogTypeEnum.buy_diamond, String.valueOf(order.getOrder_id()),
					UserIncomeAccountLogTypeEnum.buy_diamond.getDesc());
		}
		userEnergyAgent.changeEnergyAccount(entity.getUser_id(), order.getEnergy() + order.getDonor(),
				entity.getMoney(), UserEnergyAccountLogTypeEnum.recharge_account.getCode(), 1, entity.getPay_channel(),
				String.valueOf(order.getOrder_id()), entity.getDescription());
		// 分成给邀请者
		UserInviteEntity inviter = userInviteContract.findByProperty("userid", order.getUser_id());
		if (Tools.isNotNull(inviter)) {
			VacuateConfigDto vacuate = sysConfigAgent.vacuate();
			int income = Math.round(order.getPrice() * vacuate.getRechargeIncome() / 100);
			userIncomeAgent.changeIncomeAccount(inviter.getInvitation(), income, income, 1, UserIncomeAccountLogTypeEnum.recharge_back_invitation_income,
					String.valueOf(order.getOrder_id()), vacuate, UserIncomeAccountLogTypeEnum.recharge_back_invitation_income.getDesc());
		}
		order.setStatus(1);
		order.setUpdate_time(new Date());
		energyOrderContract.update(order);
		try {
			topHeadInfoContract.addTopHeadInfo(entity.getNickname(), "充值了" + entity.getMoney() / 100 + "元");
		} catch (Exception e) {
			logger.warn("recharging-diamond adds top tip error!", e);
		}
	}

}

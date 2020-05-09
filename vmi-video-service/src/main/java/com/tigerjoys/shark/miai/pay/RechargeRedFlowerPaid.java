package com.tigerjoys.shark.miai.pay;

import java.util.Date;
import java.util.List;

import org.shark.miai.common.enums.AppNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IGlobalBroadcastAgent;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IProxyAgent;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.agent.enums.FirstPayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.GlobalBroadcastTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.RechargeOrderDonorTimesEnum;
import com.tigerjoys.shark.miai.agent.enums.RechargeOrderDonorTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.RedFlowerAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.agent.enums.UserPointAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IRedFlowerOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IUserFirstRechargeLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserNoWithdrawalBoundsContract;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.UserFirstRechargeLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;

/** 
  * @author mouzhanpeng at [2017年10月11日 下午5:07:18] 
  * @since JDK 1.8.0 
  */
@Service(PayTypeEnum.REDFLOWER)
public class RechargeRedFlowerPaid extends IPayAgent.NotifyCallback{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private IUserPointAgent userPointAgent;

	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IGlobalBroadcastAgent globalBroadcastAgent;

	@Autowired
	private IRedFlowerOrderContract redFlowerOrderContract;
	
	@Autowired
	private IRedFlowerAgent redFlowerAgent;
	
	@Autowired
	private IUserNoWithdrawalBoundsContract userNoWithdrawalBoundsContract;
	
	@Autowired
	private IUserFirstRechargeLogContract userFirstRechargeLogContract;
	
	
	@Autowired
	private IProxyAgent proxyAgent;

	/**
	 * 购买小红花回调处理
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	protected void dealNotifyData(UserPayActionEntity entity) throws Exception {
		RedFlowerOrderEntity order = redFlowerOrderContract.findById(entity.getOrder_id());
		// 扣收益
		if(0 < order.getIncome()){
			userIncomeAgent.changeIncomeAccount(order.getUser_id(), order.getIncome(), 0, UserIncomeAccountLogTypeEnum.buy_red_flower, String.valueOf(order.getOrder_id()),
					UserIncomeAccountLogTypeEnum.buy_red_flower.getDesc());
			userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 0, order.getIncome());
		}
		long chargeDiamond = order.getFlower();
		if(order.getDonor_type() == RechargeOrderDonorTypeEnum.diamond.getCode()){
			if( order.getDonor()>0){
				if(order.getDonor_times() == RechargeOrderDonorTimesEnum.first.getCode() && checkFirstRecharge(order.getUser_id())){
					chargeDiamond = chargeDiamond + order.getDonor();
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.every.getCode()){
					chargeDiamond = chargeDiamond + order.getDonor();
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.nothing.getCode()){
					chargeDiamond = chargeDiamond;
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.repetition_desc.getCode() && !checkFirstRecharge(order.getUser_id())){
					chargeDiamond = chargeDiamond + order.getDonor();
				}
			}
		}
		// 充钻石
		redFlowerAgent.changeRedFlowerAccount(entity.getUser_id(), chargeDiamond,
				entity.getMoney(), RedFlowerAccountLogTypeEnum.recharge_account.getCode(), 1, entity.getPay_channel(),
				String.valueOf(order.getOrder_id()), entity.getDescription());
		//送积分
		if (entity.getMoney()/100>=100) {
			userPointAgent.changePointAccount(entity.getUser_id(), UserPointAccountLogTypeEnum.recharge_limit_award.getCode(), 1, String.valueOf(entity.getId()), UserPointAccountLogTypeEnum.recharge_limit_award.getDesc());
		}
		//把充值记录添加到全局广播
		if (entity.getMoney()>0) {
			globalBroadcastAgent.insert(entity.getUser_id(), 0, entity.getMoney()/100>=58?entity.getMoney()/100:58, GlobalBroadcastTypeEnum.recharge.getCode(), 0);
		}
		
		/**
		 * 充钻返收益
		 */
		if(order.getDonor_type() == RechargeOrderDonorTypeEnum.money.getCode()){
			if(order.getDonor() > 0){
				if(order.getDonor_times() == RechargeOrderDonorTimesEnum.first.getCode() && checkFirstRecharge(order.getUser_id())){
					userIncomeAgent.changeIncomeAccount(entity.getUser_id(), order.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_flower_back_income, String.valueOf(order.getOrder_id()),
							UserIncomeAccountLogTypeEnum.recharge_flower_back_income.getDesc());
					userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 1, order.getDonor()*100);
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.every.getCode()){
					userIncomeAgent.changeIncomeAccount(entity.getUser_id(), order.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_flower_back_income, String.valueOf(order.getOrder_id()),
							UserIncomeAccountLogTypeEnum.recharge_flower_back_income.getDesc());
					userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 1, order.getDonor()*100);
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.nothing.getCode()){
					
				}else if(order.getDonor_times() ==  RechargeOrderDonorTimesEnum.repetition_desc.getCode() && !checkFirstRecharge(order.getUser_id())){
					userIncomeAgent.changeIncomeAccount(entity.getUser_id(), order.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_flower_back_income, String.valueOf(order.getOrder_id()),
							UserIncomeAccountLogTypeEnum.recharge_flower_back_income.getDesc());
					userNoWithdrawalBoundsContract.updateBalance(order.getUser_id(), AccountType.GENERAL.getCode(), 1, order.getDonor()*100);
				}
			}
		}
		
		// 分成给邀请者
		if(AppNameEnum.andriod_com_tjhj_miliao.getPackageName().equalsIgnoreCase(order.getPackage_name())){
			proxyAgent.addCpsIncome(order.getUser_id(), order.getMoney());
		}
		
		/*
		UserInviteEntity inviter = userInviteContract.findByProperty("userid", order.getUser_id());
		if (Tools.isNotNull(inviter)) {
			PageModel pageModel = new PageModel();
			pageModel.addQuery(Restrictions.eq("user_id", order.getUser_id()));
			pageModel.addQuery(Restrictions.eq("status",1));
			if(rechargeOrderContract.count(pageModel) == 0 ){
				VacuateConfigDto vacuate = sysConfigAgent.vacuate();
				int income = Math.round(order.getPrice() * vacuate.getRechargeIncome() / 100);
				userIncomeAgent.changeIncomeAccount(inviter.getInvitation(), income, income, 1, UserIncomeAccountLogTypeEnum.recharge_redflower_back_invitation_income,
						String.valueOf(order.getOrder_id()), vacuate, UserIncomeAccountLogTypeEnum.recharge_redflower_back_invitation_income.getDesc());
			}
		}
		*/
		//优先检测首次充值
		try {
			boolean first = userFirstRechargeLogContract.checkFirstRecharge(order.getUser_id(), FirstPayTypeEnum.all.getCode());
			/*
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
			*/
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
		
		order.setStatus(1);
		order.setUpdate_time(new Date());
		redFlowerOrderContract.update(order);
	
	}
	
	
	private boolean checkFirstRecharge(long userid) throws Exception{
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.addQuery(Restrictions.eq("user_id", userid));
		List< RedFlowerOrderEntity>list = redFlowerOrderContract.load(pageModel);
		return Tools.isNull(list);
	}
	
}

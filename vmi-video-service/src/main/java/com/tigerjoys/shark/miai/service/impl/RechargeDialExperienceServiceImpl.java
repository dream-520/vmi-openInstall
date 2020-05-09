package com.tigerjoys.shark.miai.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.nbs.web.context.UserDetails;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.dto.PayAccessDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IBuyDialExperienceOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceLogContract;
import com.tigerjoys.shark.miai.inter.contract.IPayUserRecordContract;
import com.tigerjoys.shark.miai.inter.entity.BuyDialExperienceOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceLogEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.PayUserRecordEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomCategoryEntity;
import com.tigerjoys.shark.miai.service.IRechargeDialExperienceService;
import com.tigerjoys.shark.miai.service.IRechargeWithdrawalService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * @author yangjunming at [2019年10月11日 下午4:05:56]
 * @since JDK 1.8.0
 */
@Service
public class RechargeDialExperienceServiceImpl implements IRechargeDialExperienceService {


	@Autowired
	private IPayAgent payAgent;

	@Autowired
	private IUserIncomeAgent userIncomeAgent;

	@Autowired
	private ISysConfigAgent sysConfigAgent;

	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IPayUserRecordContract payUserRecordContract;
	
	@Autowired
	private IFreeVideoChatExperienceLogContract freeVideoChatExperienceLogContract;
	
	@Autowired
	private IBuyDialExperienceOrderContract buyDialExperienceOrderContract;
	
	@Autowired
	private IRechargeWithdrawalService rechargeWithdrawalService;
	

	public Map<String, Object> getPriceList() throws Exception {
		BeatContext bc = RequestUtils.getCurrent();
		long userId = bc.getUserid();
		UserBO userBO = userAgent.findById(userId);
		Map<String, Object> map = new HashMap<>();
		if(Tools.isNull(userBO)){
			return map;
		}
		
		long userCrateTime = Tools.longValue(Tools.getFormatDate(userBO.getCreateTime(), "yyyyMMdd"));
		if(20191130>userCrateTime){
			return map;
		}
		
		
		PayUserRecordEntity payUserRecordEntity = payUserRecordContract.findByProperty("userid", userId);
		if(Tools.isNotNull(payUserRecordEntity)){
			return map;
		}
		
		/*
		RechargeCustomCategoryEntity category = rechargeWithdrawalService.getRechargeCustomCategory(bc.getHeader().getMobile_model());
		if(Tools.isNotNull(category)){
			if(category.getId()>6){
				return map;
			}
		}
		*/
		String channel = bc.getHeader().getChannel();
		if("User_Share_i".equalsIgnoreCase(channel) || "User_Share_a".equalsIgnoreCase(channel) ){
			return map;
		}
		
		FreeVideoChatExperienceLogEntity freeLog = freeVideoChatExperienceLogContract.findByProperty("userid", userId);
		if(Tools.isNotNull(freeLog)){
			return map;
		}
		
		
		long price = getPrice(userId,userBO);
		if(price == 0){
			return map;
		}
		map.put("dialFalg", "1");
		map.put("dialMoney", price);
		map.put("dialActionUrl", Const.WEB_SITE+"/api/product/all/"+9+"/"+price);
		return map;
	}
	
	
	public long getPrice(long userId,UserBO userBo) throws Exception{
		String mobileModel = RequestUtils.getCurrent().getHeader().getMobile_model();
		RechargeCustomCategoryEntity categoryEntity = rechargeWithdrawalService.getRechargeCustomCategory(mobileModel);
		if (Tools.isNull(categoryEntity)) {
			return 0;
		}
		long price = 0;
		long day = (Tools.getDayTime()-Tools.getDayTime(userBo.getCreateTime()))/(24 * 60 * 60 * 1000);
		
			//'北京'4146;'天津'4149;'广州'3697;'深圳'3699;'东莞'3713
			//SELECT * FROM t_app_area WHERE NAME IN ('北京','天津','广州','深圳','东莞') AND depth=2
			if (userBo.getCityid() == 3697 || userBo.getCityid() == 3699 || userBo.getCityid() == 3713 || userBo.getCityid() == 4146 || userBo.getCityid() == 4149) {
				if (day == 0) {
					if (categoryEntity.getId() <= 7){
						price = Const.BUY_DIAL_EXPERIENCE_CURRENT_DAY;
					} else {
						price = Const.BUY_DIAL_EXPERIENCE_PROVINCE_CURRENT_DAY;
					}
				} else if (day < 2) {
					price = Const.BUY_DIAL_EXPERIENCE_LAST_15_DAY;
				}
			} else {
				if (categoryEntity.getId() <= 6){
					if (day == 0) {
						price = Const.BUY_DIAL_EXPERIENCE_CURRENT_DAY;
					} else if (day < 2) {
						price = Const.BUY_DIAL_EXPERIENCE_LAST_15_DAY;
					}
				}
			}
			
			
		return price;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult recharge(long priceId, int channelCode, double money, double income,
			Map<String, String> ipaParam) throws Exception {

		BeatContext context = RequestUtils.getCurrent();
		RequestHeader header = context.getHeader();
		UserDetails user = context.getUser();
		Long userId = user.getUserid();
		String nickname = Tools.isNull(user.getNickname()) ? "" : user.getNickname();
		String mobile = Tools.isNull(user.getMobile()) ? "" : user.getMobile();
		Date date = new Date();
		int mon = new BigDecimal(String.valueOf(money)).multiply(new BigDecimal("100")).intValue(),
				inc = new BigDecimal(String.valueOf(income)).multiply(new BigDecimal("100")).intValue();
		if (0 < inc && inc > userIncomeAgent.getIncomeBalance(userId, AccountType.GENERAL)) {
			return ActionResult.fail(ErrorCodeEnum.income_pay_not_enough);
		}
			
		GuardVipCategoryEntity price = new GuardVipCategoryEntity();
		price.setId(priceId);
		price.setMoney(Tools.intValue(priceId*100));

		if (Tools.isNotNull(price)) {
			if (mon + inc != price.getMoney() || sysConfigAgent.checkIncomeRadioNoPass(price.getMoney(), inc)) {
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
		} else {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}

		String transactionId = null;
		String receipt = null;
		if (Tools.isNotNull(ipaParam)) {
			transactionId = ipaParam.get("transactionId");
			receipt = ipaParam.get("receipt");
		}
		if (PayChannelEnum.iap.getCode() == channelCode && Tools.isNull(receipt) && Tools.isNull(transactionId)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}

		// 同一订单可能多次验证
		Long orderId = payAgent.ensureOrderViaReceipt(MD5.encode(receipt));
		
		if (null == orderId || null == buyDialExperienceOrderContract.findById(orderId)) {
			BuyDialExperienceOrderEntity order = new BuyDialExperienceOrderEntity();
			order.setOrder_id(IdGenerater.generateId());
			order.setCreate_time(date);
			order.setUpdate_time(date);
			order.setUser_id(userId);
			order.setMobile(mobile);
			order.setNickname(nickname);
			order.setPrice_id(price.getId());
			order.setPrice(price.getMoney());
			order.setMoney(mon);
			order.setIncome(inc);
			order.setStatus(0);
			order.setPackage_name(header.getPackageName());
			buyDialExperienceOrderContract.insert(order);
			orderId = order.getOrder_id();
		}

		PayAccessDto access = new PayAccessDto();
		access.setUser_id(user.getUserid());
		access.setNickname(user.getNickname());
		access.setMobile(user.getMobile());
		access.setOrder_id(orderId);
		access.setInitialPrice((long) price.getMoney());
		String fm = Tools.formatDouble2PercentToString(mon);
		access.setType(PayTypeEnum.dial_experience);
		access.setSubject("[畅聊体验：" + fm + "元]");
		access.setDescription("用户账户畅聊体验花费：" + fm + "元");
		access.setMoney((long) mon);
		access.setPay_channel(PayChannelEnum.getByCode(channelCode));
		access.setApp_type(header.getOs_type());
		access.setApp_channel(header.getChannel());
		access.setApp_version(header.getVersion());
		access.setPackage_name(header.getPackageName());
		// iap 字段
		access.setReceipt(receipt);
		access.setIpaTransactionId(transactionId);
		// access.setProduct_id(price.getPrice_identifier());
		return ServiceHelper.dealPayData(payAgent.preparePay(access), PayChannelEnum.getByCode(channelCode));

	}

}

package com.tigerjoys.shark.miai.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.enums.RechargeCategoryPriceEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserBuyWatchPopLogContract;
import com.tigerjoys.shark.miai.inter.entity.RechargePriceEntity;
import com.tigerjoys.shark.miai.inter.entity.UserBuyWatchPopLogEntity;
import com.tigerjoys.shark.miai.service.IRechargeGuardVipService;
import com.tigerjoys.shark.miai.service.IRechargeWithdrawalService;

/**
 * @Description: 充值提现
 * @author mouzhanpeng
 * @date 2017年5月11日 下午2:52:03
 * @version
 * @since 1.8.0
 */
@Controller


//@TestEncrypt("pUOj7GGbnHNF3q72/4D9sUl6azlJzO/JJO32FZQzJZUdZiFVVXvbeykhsm46C8YGZSDqq/WmmQHEdpBggqomGAWOA8qFvlPnYyg40iFAVEirGBNeJqxO0jsZcpQS7HQ7h7tFh+X256Ro4gDNGv6w/VC0sDAMP0Pw/Yr3xLwtmdPZyzlFeuj1qY4up4/inEueyTzwbjiwhD15bkg+CwuqasxEXF4md04jqIZbaV2NTPJEPgcBp+2p4ZLGT07a+B1EhK+pjn93gzj7ULujX1yX4RmpZ7yy4kCQrD87MDGC8bbfD696GlvW+tuaudSc7nl2xn0/mgYl4FF6fqb3cknrzbkFjP+EkUAl5vkrhvEIMEoQHLyWGVAQ5LR+sbEsgE9aOAliQya6dk9fETRXvjdz2/fOKvWStPB50TFSjjtKh3731MRd74O73QbSKBxIW4NOSvROEjJe/1wVNr83iFwQGaPEH85RFFrzmaUcA9TMHi1HlKi4T1rkVTEUGuTY4NHb")

@RequestMapping(value = "/recharge/category", produces = Produce.TEXT_ENCODE)
public class RechargeGuardVipController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IRechargeWithdrawalService rechargeWithdrawalService;
	
	@Autowired
	private IRechargeGuardVipService rechargeGuardVipService;

	@Autowired
	private IUserBuyWatchPopLogContract userBuyWatchPopLogContract;
	
	
	/**
	 * VIP弹窗
	 * 
	 * @return ActionResult
	 */
	@UserClientService("recharge")
	@Login
	@RequestMapping(value = "/vip/pop/{priceId}", produces = Produce.TEXT_HTML)
	public String payH5(@PathVariable long priceId,Model model) throws Exception{
		RechargePriceEntity entity = rechargeWithdrawalService.getRechargeCustomPrice(priceId);
		model.addAttribute("money",Const.vipBuyExperienceDto.getMoney());
		model.addAttribute("days",Const.vipBuyExperienceDto.getDays());
		model.addAttribute("dialActionUrl", Const.WEB_SITE+"/api/product/all/"+RechargeCategoryPriceEnum.VIP.getCode()+"/"+Const.vipBuyExperienceDto.getPriceId());
		UserBuyWatchPopLogEntity log = new UserBuyWatchPopLogEntity();
		log.setCreate_time(new Date());
		log.setUserid(RequestUtils.getCurrent().getUserid());
		log.setType(RechargeCategoryPriceEnum.VIP.getCode());
		userBuyWatchPopLogContract.insertIgnore(log);
		return "activity/goddess/telExpensePop";
	}
	
	
	/**
	 * IOS版h5支付页面
	 * 
	 * @return ActionResult
	 */
	@UserClientService("recharge")
	@Login
	@RequestMapping(value = "/popLog/{type}", produces = Produce.TEXT_HTML)
	public ActionResult popLog(@PathVariable int type) throws Exception{
		UserBuyWatchPopLogEntity log = new UserBuyWatchPopLogEntity();
		log.setCreate_time(new Date());
		log.setUserid(RequestUtils.getCurrent().getUserid());
		log.setType(type);
		userBuyWatchPopLogContract.insertIgnore(log);
		return ActionResult.success();
	}
	
	
	/**
	 * 购买VIP弹窗
	 * 
	 * @return ActionResult
	 */
	@UserClientService("recharge")
	@Login
	@RequestMapping(value = "/chargeVipList", produces = Produce.TEXT_HTML)
	public String chargeVipList(Model model) throws Exception{
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		Map<String, Object> outMap = rechargeGuardVipService.getVipPriceList(header.getMobile_model());
		model.addAllAttributes(outMap);
		logger.info("chargeVipList:"+JsonHelper.toJson(outMap));
		//TODO:前端给页面
		return "integral/goH5Vip";
	}
	
	
}

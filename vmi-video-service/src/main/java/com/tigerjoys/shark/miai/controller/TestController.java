package com.tigerjoys.shark.miai.controller;

import java.util.HashMap;
import java.util.Map;

import org.shark.miai.common.cloud.storage.ICloudStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.ApiResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.utils.SpringBeanApplicationContext;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IPayAgent.NotifyCallback;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.pay.RechargeDialExperiencePaid;
import com.tigerjoys.shark.miai.pay.RechargeDiamondPaid;
import com.tigerjoys.shark.miai.pay.RechargeGuardPaid;
import com.tigerjoys.shark.miai.pay.RechargeRedFlowerPaid;
import com.tigerjoys.shark.miai.pay.RechargeVipPaid;
import com.tigerjoys.shark.miai.pay.RechargeWeekCardPaid;
import com.tigerjoys.shark.pay.client.IPayCenterClient;
import com.tigerjoys.shark.pay.client.PayCenterClientBuilder;
import com.tigerjoys.shark.pay.client.dto.PayCenterSubmitOrderDto;
import com.tigerjoys.shark.pay.client.dto.PayCenterSubmitOrderResultDto;
import com.tigerjoys.shark.pay.client.enums.PayCenterChannelEnums;
import com.tigerjoys.shark.pay.client.enums.PayCenterCodeEnums;
import com.tigerjoys.shark.pay.client.enums.PayCenterOsTypeEnums;

@Controller
@RequestMapping(value="/api/test")
//@TestEncrypt("5KdAVyLCYWMpEV/67SjhMSy8JbnsvtHunHwQxXH5gDhleyn6v+3Inj3+qLBRBpka8NCFEHexNL3+9vy7k4FMWLTcaAjORaGwvZyaDWF1XSAVJNDiHQprstdYGb1uqQnuzYumOxpx9Vuu5qm0+AmeIszUsVBOyuFwJCYgHPTD0CFRaczsh6aEqMk03WM3ly/Ymd+4bBLbVa3eSsXn6ErREGYnf/kRSXW1BIL9ZeGQ1hYlfoB/Xj2ZVpH2J5ePOCHKKnDNW8AqYbZFoVT/ygpyymgIs6vtTUc0w1tlKpYyrxufhWRu6x58bix9oNDXedvVEUPtBWRDzApWG2kBoGtkpX7dk909VYpxDLtzeWenQIPba5DWHyKeIoeQzfQejbveaGxP5oz4yAKmUpFwzv+AiXJvDfuKu3CBSje6wwbdaY8=")
public class TestController extends BaseController {

	@Autowired
	private RechargeDiamondPaid rechargeDiamondPaid;
	
	@Autowired
	private RechargeRedFlowerPaid rechargeRedFlowerPaid;
	
	
	@Autowired
	private RechargeGuardPaid rechargeGuardvipPaid;
	
	@Autowired
	private RechargeVipPaid rechargeVipPaid;
	
	@Autowired
	private RechargeWeekCardPaid rechargeWeekCardPaid;
	
	
	@Autowired
	private IUserPayActionContract userPayActionContract;
	
	@Autowired
	private RechargeDialExperiencePaid rechargeDialExperiencePaid;
	
	@Autowired
	@Qualifier("upYunCloudStorage")
	private ICloudStorage upYunCloudStorage;
	
	@FilterHeader
	@RequestMapping(value="/submitOrder/{orderno}", produces=Produce.TEXT_JSON)
	public @ResponseBody ActionResult testSubmitOrder(@PathVariable("orderno") String orderno) throws Exception {
		PayCenterSubmitOrderDto submitOrder = new PayCenterSubmitOrderDto();
		submitOrder.setOs_type(PayCenterOsTypeEnums.ANDROID.getCode());
		submitOrder.setPay_channel(PayCenterChannelEnums.ALIPAY_H5.getCode());
		submitOrder.setApp_channel("Huawei_yoyo");
		submitOrder.setApp_version("5.3.0");
		submitOrder.setApp_package("com.ydwx.yoyo");
		submitOrder.setTotal_amount(10000);
		submitOrder.setProduct_id("CT_VIP_100");
		submitOrder.setSpbill_create_ip("127.0.0.1");
		submitOrder.setOut_order_no(orderno);
		submitOrder.setNotify_url("http://www.liaomei.com/api/notify");
		submitOrder.setReturn_url("http://www.liaomei.com/api/return");
		submitOrder.setSubject("钻石100枚");
		submitOrder.setDescription("打一辈子工都得不到的东西钻石100枚是个什么概念，就是你我一辈子都赚不到，打一辈子工都得不到的东西");
		
		IPayCenterClient payCenterClient = PayCenterClientBuilder.builder("10000000", "123456789", "http://127.0.0.1:8080");
		
		ApiResult<PayCenterSubmitOrderResultDto> result = payCenterClient.submitOrder(submitOrder);
		if(result.getCode() == PayCenterCodeEnums.SUCCESS.getCode()) {
			Map<String, Object> data = new HashMap<>();
			data.put("channel", PayCenterChannelEnums.ALIPAY_H5.getCode());
			data.put("signData", result.getData().getSign_data());
			
			return ActionResult.success(data);
		} else {
			return ActionResult.fail(result.getCode() , result.getCodemsg());
		}
	}
	
	@FilterHeader
	@RequestMapping(value="/start", produces=Produce.TEXT_HTML)
	public String dynamicTest(Model model) throws Exception {
		return "test";
	}
	
	@FilterHeader
	@RequestMapping(value="/recharge")
	public @ResponseBody ActionResult rechargeDiamond123(@RequestParam("orderid") String orderid) throws Exception {
		if(Const.IS_TEST) {
			UserPayActionEntity entity = userPayActionContract.findById(Long.parseLong(orderid));
			/*
			if(Tools.isNotNull(entity) && entity.getStatus() == 0 && entity.getType().equals(PayTypeEnum.recharge_diamond.getCode())) {
				rechargeDiamondPaid.notifyData(entity);
				entity.setStatus(1);
				userPayActionContract.update(entity);
				return ActionResult.success();
			} 
			if(Tools.isNotNull(entity) && entity.getStatus() == 0 && entity.getType().equals(PayTypeEnum.recharge_red_flower.getCode())) {
				rechargeRedFlowerPaid.notifyData(entity);
				entity.setStatus(1);
				userPayActionContract.update(entity);
				return ActionResult.success();
			} 
			if(Tools.isNotNull(entity) && entity.getStatus() == 0 && entity.getType().equals(PayTypeEnum.recharge_guard.getCode())) {
				rechargeGuardvipPaid.notifyData(entity);
				entity.setStatus(1);
				userPayActionContract.update(entity);
				return ActionResult.success();
			} 
			if(Tools.isNotNull(entity) && entity.getStatus() == 0 && entity.getType().equals(PayTypeEnum.recharge_vip.getCode())) {
				rechargeVipPaid.notifyData(entity);
				entity.setStatus(1);
				userPayActionContract.update(entity);
				return ActionResult.success();
			} 
			if(Tools.isNotNull(entity) && entity.getStatus() == 0 && entity.getType().equals(PayTypeEnum.room_weeks_card.getCode())) {
				rechargeWeekCardPaid.notifyData(entity);
				entity.setStatus(1);
				userPayActionContract.update(entity);
				return ActionResult.success();
			} 
			if(Tools.isNotNull(entity) && entity.getStatus() == 0 && entity.getType().equals(PayTypeEnum.dial_experience.getCode())) {
				rechargeDialExperiencePaid.notifyData(entity);
				entity.setStatus(1);
				userPayActionContract.update(entity);
				return ActionResult.success();
			} 
			*/
			if(Tools.isNotNull(entity) && entity.getStatus() == 0){
				SpringBeanApplicationContext.getBean(PayTypeEnum.getByCode(entity.getType()).name(), NotifyCallback.class).notifyData(entity);
				entity.setStatus(1);
				userPayActionContract.update(entity);
				return ActionResult.success();
			}
			return ActionResult.fail(1001,"输入的商品id不存在或者已经使用了");
			
		}
		return ActionResult.fail(1001,"线上环境不能调用");
	}
	
	@Login
	@RequestMapping(value = "/pay", produces = Produce.TEXT_HTML)
	public String authentication(Model model) throws Exception {
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		return "pay/pay";
	}
	
	@FilterHeader
	@RequestMapping(value="/aaaa/test", produces=Produce.TEXT_JSON)
	public @ResponseBody ActionResult aasdasdasd() throws Exception {
		//upYunCloudStorage.writeFile("vmi2/test/111.txt", "1232333", true);
		System.out.println(upYunCloudStorage.readFile("vmi2/test/111.txt"));
		byte[] bb = upYunCloudStorage.readFileBytes("vmi2/test/111.txt");
		System.out.println(new String(bb,"UTF-8"));
		
		return ActionResult.success();
	}
	
}

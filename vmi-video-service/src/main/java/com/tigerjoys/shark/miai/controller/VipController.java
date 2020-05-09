package com.tigerjoys.shark.miai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.pay.IapConfig;
import com.tigerjoys.shark.miai.agent.pay.IapHelper;
import com.tigerjoys.shark.miai.service.IVipService;

/**
 *	vip控制类
 * @author yangjunming
 *
 */
@Login
@Controller
@RequestMapping(value="/api/vip" , method=RequestMethod.POST , produces=Produce.TEXT_ENCODE)
public class VipController extends BaseController {
	
	
	
	@Autowired
	private IVipService vipService;
	
	
	
	@RequestMapping(value="/vipList",method=RequestMethod.POST)
	@Login
	public @ResponseBody ActionResult queryVip() throws Exception {
		BeatContext context= RequestUtils.getCurrent();
		Long userId=context.getUserid();
		return vipService.queryVip(userId);
		
	}
	
	@RequestMapping(value="/buyVip",method=RequestMethod.POST)
	@Login
	public @ResponseBody ActionResult buyVip(@RequestBody String body) throws Exception {
		BeatContext context= RequestUtils.getCurrent();
		Long userId=context.getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		String diamondStr = json.getString("diamond");
		String orderId = json.getString("orderId");
		long vipId = json.getLong("vipId");
		int diamond=Tools.parseInt(diamondStr);
		ActionResult result=vipService.buyVip(userId, orderId, vipId, diamond);
		if(result.getCode()!=0){
			return result;
		}
		return vipService.queryVip(userId);
	}
	
	@RequestMapping(value="/buyVipUsedEneryg",method=RequestMethod.POST)
	@Login
	public @ResponseBody ActionResult buyVipUsedEnergy(@RequestBody String body) throws Exception {
		BeatContext context= RequestUtils.getCurrent();
		Long userId=context.getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		String enerygStr = json.getString("eneryg");
		String orderId = json.getString("orderId");
		long vipId = json.getLong("vipId");
		int eneryg=Tools.parseInt(enerygStr);
		ActionResult result=vipService.buyVipUsedEnergy(userId, orderId, vipId, eneryg);
		if(result.getCode()!=0){
			return result;
		}
		return vipService.queryVip(userId);
	}
	
	
	@Login
	@RequestMapping("/wrap/data")
	@ResponseBody
	public ActionResult wrapPayData(@RequestBody String body) {
		try {
			JSONObject json = JsonHelper.toJsonObject(body);
			String payStr = IapHelper.getPayData(json.getString("receipt"));
			JSONObject payData = JsonHelper.toJsonObject(payStr);
			int status = payData.getIntValue("status");
			if (0 == status) {
				return ActionResult.success();
			} else if(21007 == status){// 沙盒测试数据标识
				payStr = IapHelper.getPayData(json.getString("receipt"), IapConfig.DEV_VERIFY_URL);
				payData = JsonHelper.toJsonObject(payStr);
				if(0 == payData.getIntValue("status")){
					return ActionResult.success();
				}else{
					return ActionResult.fail(status, "[沙盒苹果服务器]未响应数据！");
				}
			}else{
				return ActionResult.fail(status, "[线上苹果服务器]未响应数据！");
			}
		} catch (Exception e) {
			logger.error("包装请求数据过程出错", e);
			return ActionResult.fail();
		}
	}
}

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
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.service.IVChatVideoService;

@Controller
@RequestMapping(value = "/api/vchat", method=RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class VChatVideoController {

	
	@Autowired
	private IVChatVideoService vChatVideoService;

	
	
	/**
	 * 获对视频通话对方信息
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/dialing", method=RequestMethod.POST)
	public @ResponseBody ActionResult dialing(@RequestBody String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long toUserId = json.getLong("toUserId");
		return vChatVideoService.dialing(userId, toUserId);
	}
	
	/**
	 * 视频通话应答接口 （接听，进入，退出）
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/communicateRes", method=RequestMethod.POST)
	public @ResponseBody ActionResult communicateRes(@RequestBody String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long orderId = json.getLong("orderId");
		Long toUserId = json.getLong("toUserId");
		int status = json.getIntValue("status");
		
		return vChatVideoService.communicateRes(userId,orderId, toUserId,status);
	}
	
	
	/**
	 * IOS是否能进入房间
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/isEnterRoom", method=RequestMethod.POST)
	public @ResponseBody ActionResult isEnterRoom(@RequestBody String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long orderId = json.getLong("orderId");
		return vChatVideoService.isEnterRoom(orderId);
	}

	
	/**
	 * 视频通话支付接口
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/payOrder", method=RequestMethod.POST)
	public @ResponseBody ActionResult payOrder(@RequestBody String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long orderId = json.getLong("orderId");
		
		return vChatVideoService.payOrder(userId,orderId);
	}
	
}

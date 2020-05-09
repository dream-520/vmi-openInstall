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
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.service.IUserSubscribeAnchorService;

/**
 * 关于用户预约主播相关的业务接口
 * @author shiming
 */
@Login
@Controller
@RequestMapping(value="/api/subscribe", produces=Produce.TEXT_ENCODE)
public class UserSubscribeAnchorController {

	@Autowired
	private IUserSubscribeAnchorService userSubscribeAnchorService;
	
	/**
	 * 检测是否有预约关系
	 * @param body
	 */
	@RequestMapping(value="/check",method=RequestMethod.POST)
	public @ResponseBody ActionResult checkSubscribe(@RequestBody(required = false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		long anchorid = 0;
		JSONObject obj = JsonHelper.toJsonObject(body);
		if(Tools.isNotNull(obj))
			anchorid = obj.getLongValue("userid");
		if(userId > 0 && anchorid > 0)
			return userSubscribeAnchorService.checkSubscribe(userId, anchorid);
		return ActionResult.fail(ErrorCodeEnum.parameter_error);
	}
	
	/**
	 * 提交预约信息
	 * @param body
	 */
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public @ResponseBody ActionResult commitSubscribe(@RequestBody(required = false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		long anchorid = 0;
		JSONObject obj = JsonHelper.toJsonObject(body);
		if(Tools.isNotNull(obj))
			anchorid = obj.getLongValue("userid");
		if(userId > 0 && anchorid > 0)
			return userSubscribeAnchorService.commitSubscribe(userId, anchorid);
		return ActionResult.fail(ErrorCodeEnum.parameter_error);
	}
	
	/**
	 * 获取对应的预约列表数据
	 * @param body
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody ActionResult userSubscribe() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		if(userId > 0)
			return userSubscribeAnchorService.getSubscribes(userId);
		return ActionResult.fail(ErrorCodeEnum.parameter_error);
	}
	
}

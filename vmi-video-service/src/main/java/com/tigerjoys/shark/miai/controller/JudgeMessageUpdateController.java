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
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.service.IMessageService;

/**
 * 判断各种消息是否有更新
 * @author liuman
 *
 */
@Controller
@RequestMapping(value="/api",produces=Produce.TEXT_ENCODE)
public class JudgeMessageUpdateController extends BaseController {
	
	@Autowired
	private IMessageService messageService;
	
	/**
	 * 
	 * 判断消息是否更新
	 * @param body
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="/message/juadgeUpdate",method=RequestMethod.POST)
//	public @ResponseBody ActionResult juadgeUpdate(@RequestBody(required = false) String body) throws Exception {
//		long userId = RequestUtils.getCurrent().getUserid();
//		JSONObject json = JsonHelper.toJsonObject(body);
//		String stamp = json.getString("stamp");//系统消息发布的关键戳
//		String packageName = RequestUtils.getCurrent().getHeader().getPackageName();
//		return messageService.getMessageUpdateDtos(userId,stamp,packageName);
//	}
	
	/**
	 * 判断消息是否更新
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/message/juadgeUpdate",method=RequestMethod.POST)
	public @ResponseBody ActionResult juadgeUpdate(@RequestBody(required = false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		return messageService.getMessageCount(userId);
	}
	
}

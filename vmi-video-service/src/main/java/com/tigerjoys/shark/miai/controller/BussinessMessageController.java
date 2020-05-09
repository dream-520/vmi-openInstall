package com.tigerjoys.shark.miai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.service.IBussinessMessageService;

/**
 * 业务消息接口
 * @author liuman
 *
 */
@Login
@Controller
@RequestMapping(value="/api/bussinessMessage" , method=RequestMethod.POST , produces=Produce.TEXT_ENCODE)
public class BussinessMessageController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BussinessMessageController.class);
	
	@Autowired
	private IBussinessMessageService bussinessMessageService;
	
	/**
	 * 业务消息列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody ActionResult messageList(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		
		long userid = RequestUtils.getCurrent().getUserid();
		String stamp = json.getString("stamp");
		int page = json.getIntValue("page");
		int pagesize = json.getIntValue("pagesize");
		
		try {
			return bussinessMessageService.messageList(userid, stamp, page, pagesize);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return ActionResult.fail();
	}
	
}

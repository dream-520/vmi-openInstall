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
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.annotations.WaiterActionOnline;
import com.tigerjoys.shark.miai.service.IAnchorHelperAutoDialService;
import com.tigerjoys.shark.miai.service.IAnchorHelperService;

/**
 *	vip控制类
 * @author yangjunming
 *
 */
@Login
@Controller
@RequestMapping(value="/api/anchorHelper" , method=RequestMethod.POST , produces=Produce.TEXT_ENCODE)
public class AnchorHelperController extends BaseController {
	
	@Autowired
	private IAnchorHelperService anchorHelperService;
	
	@Autowired
	private IAnchorHelperAutoDialService anchorHelperAutoDialService;
	
	/**
	 * 推荐在线用户列表
	 * @return
	 * @throws Exception
	 */
	@Login
	@WaiterActionOnline
	@RequestMapping(value = "/richUserOnlineList", method = RequestMethod.POST)
	public @ResponseBody ActionResult richUserOnlineList(@RequestBody(required=false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		int tabType = json.getIntValue("tabType");
		return anchorHelperService.richUserOnlineListNew(userId,tabType);
	}
	
	
	/**
	 * 推荐在线用户列表
	 * @return
	 * @throws Exception
	 */
	@Login
	@WaiterActionOnline
	@RequestMapping(value = "/autoDial", method = RequestMethod.POST)
	public @ResponseBody ActionResult autoDial(@RequestBody(required=false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		return anchorHelperAutoDialService.autoDial(userId);
	}
	
}

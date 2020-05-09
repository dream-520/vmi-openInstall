package com.tigerjoys.shark.miai.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.tigerjoys.shark.miai.service.IAnchorDefendService;

@Login
@Controller
@RequestMapping(value = "/api/defend", method = RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class AnchorDefendController {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IAnchorDefendService anchorDefendService;
	
	@RequestMapping(value = "/anchor", method=RequestMethod.POST)
	public @ResponseBody ActionResult getAnchorDefend(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		Long userid = json.getLong("userid");
		return anchorDefendService.getAnchorDefend(userid);
	}
	
	@RequestMapping(value = "/user", method=RequestMethod.POST)
	public @ResponseBody ActionResult getUserDefend() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		return anchorDefendService.getUserDefend(userId);
	}
	
}

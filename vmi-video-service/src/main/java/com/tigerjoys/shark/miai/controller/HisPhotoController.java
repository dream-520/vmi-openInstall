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
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.annotations.WaiterActionOnline;
import com.tigerjoys.shark.miai.service.IUserPhotoService;

/**
 * 主播相册列表
 * @author chengang
 */
@Login
@Controller
@RequestMapping(value = "/api/his/photo", method = RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class HisPhotoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HisPhotoController.class);
	
	@Autowired
	private IUserPhotoService userPhotoService;
	
	/**
	 * 相册列表
	 * @param body
	 * @return ActionResult
	 * @throws Exception
	 */
	@Login
	@WaiterActionOnline
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody ActionResult photoList(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		String stamp = json.getString("stamp");
		Long anchorId = json.getLong("userid");
		long userId = RequestUtils.getCurrent().getUserid();
		
		try {
			return userPhotoService.userPhotoList(userId, anchorId, stamp);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return ActionResult.fail();
	}

}

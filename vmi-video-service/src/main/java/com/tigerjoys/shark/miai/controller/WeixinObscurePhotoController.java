package com.tigerjoys.shark.miai.controller;

import java.util.List;
import java.util.Map;

import org.shark.miai.common.enums.PlatformEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.api.client.util.Maps;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.enums.PhotoCheckedLogTypeEnum;
import com.tigerjoys.shark.miai.annotations.WaiterActionOnline;
import com.tigerjoys.shark.miai.dto.service.UserPhotoDto;
import com.tigerjoys.shark.miai.dto.service.UserVideoDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.service.IUserPhotoService;
import com.tigerjoys.shark.miai.service.IUserService;

/**
 * 私密相册/视频购买和列表等
 * @author chengang
 *
 */
@Login
@Controller
@RequestMapping(value = "/wx/obscrue", produces=Produce.TEXT_JSON)
public class WeixinObscurePhotoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinObscurePhotoController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserPhotoService userPhotoService;

	/**
	 * 付费查看主播私密作品
	 * @param body
	 * @return ActionResult
	 * @throws Exception
	 */
	@Login
	@UserClientService("wx")
	@RequestMapping(value="/look/privacy/photo")
	@ResponseBody
	public ActionResult lookPrivacyPhoto(@RequestBody String body) throws Exception {
		try {
			JSONObject json = JsonHelper.toJsonObject(body);
			
			long photoId = json.getLongValue("photoId");
			int type = json.getIntValue("type");//作品类型
			PhotoCheckedLogTypeEnum logType = PhotoCheckedLogTypeEnum.getByCode(type);
			if(photoId <= 0 || logType == null) {
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
			
			return userService.lookPrivacyPhoto(photoId, logType, PlatformEnum.H5);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ActionResult.fail();
	}
	
	/**
	 * 相册/视频列表
	 * @param body
	 * @return ActionResult
	 * @throws Exception
	 */
	@Login
	@WaiterActionOnline
	@RequestMapping(value = "/photo/list", method = RequestMethod.POST)
	public @ResponseBody ActionResult photoList(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		//String stamp = json.getString("stamp");
		Long anchorId = json.getLong("anchorId");
		long userId = RequestUtils.getCurrent().getUserid();
		
		try {
			List<UserPhotoDto> photoList = userPhotoService.getUserPhotoList(userId, anchorId, 20, null);
			List<UserVideoDto> videoList = userPhotoService.getUserVideoList(userId, anchorId, 20, null);
			
			Map<String , Object> dataMap = Maps.newHashMap();
			dataMap.put("albumList", photoList);
			dataMap.put("videoList", videoList);
			
			return ActionResult.success(dataMap);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return ActionResult.fail();
	}

}

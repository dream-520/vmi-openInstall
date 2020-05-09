package com.tigerjoys.shark.miai.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.service.IAnchorAudioService;

/**
 * 主播音频相关服务
 * @author shiming
 */
@Login
@Controller
@RequestMapping(value = "/api/anchor/audio", method = RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class AnchorAudioController {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IAnchorAudioService IAnchorAudioService;
	
	@NoSign
	@RequestMapping(value = "/publish", produces=Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult publishAudio(@RequestParam("parameters") String parameters , @RequestParam("voicefile") MultipartFile voicefile) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		int time = 0;
		if(Tools.isNotNull(parameters)) {
			parameters = AESCipher.aesDecryptString(parameters);
			logger.info("解密后======================parameters"+parameters);
			JSONObject json = JsonHelper.toJsonObject(parameters);
			time = json.getIntValue("time");
		} else {
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), "参数解析错误");
		}
		return IAnchorAudioService.audioUpload(userId, time, voicefile);
	}
	
	@RequestMapping(value = "/list", produces=Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult getAnchorAudios() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		return IAnchorAudioService.getAnchorAudios(userId);
	}
	
	@RequestMapping(value = "/select", produces=Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult selectAnchorAudio(@RequestBody(required=true) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		if(Tools.isNotNull(json)) {
			long audioId = json.getLongValue("audioId");
			if(audioId > 0) {
				return IAnchorAudioService.selectAnchorAudio(userId, audioId);
			}
		}
		return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), "参数解析错误");
	}
	
	@RequestMapping(value = "/delete", produces=Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult deleteAnchorAudio(@RequestBody(required=true) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		if(Tools.isNotNull(json)) {
			long audioId = json.getLongValue("audioId");
			if(audioId > 0) {
				return IAnchorAudioService.deleteAnchorAudio(userId, audioId);
			}
		}
		return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), "参数解析错误");
	}
	
}

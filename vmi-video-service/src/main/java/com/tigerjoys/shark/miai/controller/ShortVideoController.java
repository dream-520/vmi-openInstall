package com.tigerjoys.shark.miai.controller;

import org.shark.miai.common.annotation.AppVersion;
import org.shark.miai.common.enums.AppNameEnum;
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
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.annotations.WaiterActionOnline;
import com.tigerjoys.shark.miai.service.IShortVideoService;

/**
 * 短视频
 * @author yangjunming
 */
@Login
@Controller
@RequestMapping(value = "/api/shortVideo", method = RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class ShortVideoController extends BaseController {

	@Autowired
	private IShortVideoService shortVideoService;
	


	@Login
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public @ResponseBody ActionResult videoHome(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		String stamp = json.getString("stamp");
		return shortVideoService.videoHome(stamp);
	}
	
	@Login
	@RequestMapping(value = "/videoDesc", method = RequestMethod.POST)
	public @ResponseBody ActionResult videoDesc(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long userid = RequestUtils.getCurrent().getUserid();
		Long videoId = json.getLong("videoId");
		return shortVideoService.videoDesc(videoId,userid);
	}
	
	@Login
	@RequestMapping(value = "/anchorVideoList", method = RequestMethod.POST)
	public @ResponseBody ActionResult anchorVideoList(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		
		Long userId = json.getLong("userId");
		if(Tools.isNull(userId)){
			userId = RequestUtils.getCurrent().getUserid();
		}
		String stamp = json.getString("stamp");
		return shortVideoService.anchorVideoList(userId,stamp);
	}
	

	@NoSign
	@RequestMapping(value = "/videoUpload",produces=Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult videoUpload( @RequestParam("videofile") MultipartFile videofile) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		return shortVideoService.videoUpload(userId,videofile);
	}
	
	
	@Login
	@RequestMapping(value = "/videoDelete", method = RequestMethod.POST)
	public @ResponseBody ActionResult videoDelete(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		Long videoId = json.getLong("videoId");
		return shortVideoService.videoDelete(videoId);
	}

	@Login
	@RequestMapping(value = "/videoPraise", method = RequestMethod.POST)
	public @ResponseBody ActionResult videoPraise(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long userid = RequestUtils.getCurrent().getUserid();
		Long videoId = json.getLong("videoId");
		return shortVideoService.videoPraise(videoId,userid);
	}
	
	@AppVersion(AppNameEnum.andriod_com_tjhj_miliao)
	@Login
	@RequestMapping(value = "/anchorSlideList", method = RequestMethod.POST)
	public @ResponseBody ActionResult anchorSlideList(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		Integer tag = json.getInteger("tag");   // 0 首页  1 视频  2 个人主页下的短视频
		Integer type = json.getInteger("type"); //
		String stamp = json.getString("stamp");
		Long anchorId = json.getLong("userid");
		long userid = RequestUtils.getCurrent().getUserid();
		return shortVideoService.anchorSlideList(userid,tag,type,stamp,anchorId);
	}
	
	@Login
	@WaiterActionOnline
	@RequestMapping(value = "/anchorNewSlideList", method = RequestMethod.POST)
	public @ResponseBody ActionResult anchorNewSlideList(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		Integer tag = json.getInteger("tag");   // 0 首页  1 视频  2 个人主页下的短视频
		Integer type = json.getInteger("type"); //
		String stamp = json.getString("stamp");
		Long anchorId = json.getLong("userid");
		long userid = RequestUtils.getCurrent().getUserid();
		
		return shortVideoService.anchorNewSlideList(userid,tag,type,stamp,anchorId);
	}

	
	@Login
	@WaiterActionOnline
	@RequestMapping(value = "/shortVideoDescList", method = RequestMethod.POST)
	public @ResponseBody ActionResult shortVideoDescList(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		String stamp = json.getString("stamp");
		Long anchorId = json.getLong("userId");
		long userid = RequestUtils.getCurrent().getUserid();
		
		return shortVideoService.anchorNewSlideList(userid,1,5,stamp,anchorId);
	}
	
}

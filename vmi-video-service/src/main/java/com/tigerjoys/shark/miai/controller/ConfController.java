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
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.service.IConfService;

/**
 * 设置controller
 * @author lipeng
 *
 */
@Login
@Controller
@RequestMapping(value="/api", produces=Produce.TEXT_ENCODE)
public class ConfController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfController.class);
	
	@Autowired
	private IConfService confService;
	
	/**
	 * 软件升级
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@UserClientService("checkversion")
	@RequestMapping(value="/checkversion")
	public @ResponseBody ActionResult checkVersion(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		String channelName = json.getString("channel");//渠道
		int platform = json.getIntValue("platform");//平台
		return confService.checkVersion(channelName , platform);
	}
	
	/**
	 * IOS企业包升级检测
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@UserClientService("checkIosVersion")
	@RequestMapping(value="/checkIosVersion")
	public @ResponseBody ActionResult checkIosVersion() throws Exception {
		return confService.checkIosVersion();
		//return confService.checkVersion(RequestUtils.getCurrent().getHeader().getChannel() ,3);
	}
	
	/**
	 * IOS端 是否展示真实应用接口
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@UserClientService("realOrFake")
	@RequestMapping(value="/realOrFake")
	public @ResponseBody ActionResult getIosStatus() throws Exception {
		return confService.getIosStatus();
	}
	
	/**
	 * IOS端 是否展示真实应用接口
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@UserClientService("realOfZdkj")
	@RequestMapping(value="/app/realOfZdkj")
	public @ResponseBody ActionResult getRealOfZdkj() throws Exception {
		return confService.getIosStatus();
	}
	
	/**
	 * 黑名单列表
	 */
	@UserClientService("blackList")
	@RequestMapping(value="/blackList",method=RequestMethod.POST)
	public @ResponseBody ActionResult blackList(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		
		long userid = RequestUtils.getCurrent().getUserid();
		long stamp = json.getLongValue("stamp");
		
		try {
			return confService.blackList(userid, stamp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return ActionResult.fail();
	}
	
	/**
	 * 2.3版本使用的进行数据同步处理的接口
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@UserClientService("syncAppointType")
	@RequestMapping(value="/sync/data",method=RequestMethod.POST)
	public @ResponseBody ActionResult syncData(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		//这个需要把1设置为json.getIntValue("version")  这个地方方便测试写成了1
		return confService.syncData(json.getIntValue("version"),0);
	}
	
	/**
	 * 2.3版本使用的进行数据同步处理的接口
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@UserClientService("syncAppointType")
	@RequestMapping(value="anchor/sync/data",method=RequestMethod.POST)
	public @ResponseBody ActionResult anchorDVsyncData(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		//这个需要把1设置为json.getIntValue("version")  这个地方方便测试写成了1
		return confService.syncData(json.getIntValue("version"),1);
	}
	
	/**
	 * 用户隐私设置页面接口
	 */
	@UserClientService("privacySet")
	@RequestMapping(value="/conf/privacySet",method=RequestMethod.POST)
	public @ResponseBody ActionResult privacySet() throws Exception {
		return confService.getPrivacySet();
	}
	
	
	/**
	 * 用户隐私密码设置/验证
	 */
	@UserClientService("verifyPassword")
	@RequestMapping(value="/conf/verifyPassword",method=RequestMethod.POST)
	public @ResponseBody ActionResult privacySet(@RequestBody String body) throws Exception {
		return confService.verifyPassword(JsonHelper.toJsonObject(body));
	}
	
	/**
	 * 达人秀开关
	 */
	@UserClientService("talentOnOff")
	@RequestMapping(value="/conf/talentOnOff",method=RequestMethod.POST)
	public @ResponseBody ActionResult talentOnOff() throws Exception {
		return confService.talentOnOff();
	}
	
	
	
}

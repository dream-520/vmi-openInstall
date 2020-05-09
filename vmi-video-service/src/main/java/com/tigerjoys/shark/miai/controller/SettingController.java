package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.service.IUserAgentService;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPrivacySecurityContract;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPrivacySecurityEntity;

/**
 * 设置界面的配置
 * @author shiming
 *
 */
@Controller
@RequestMapping(value = "/api")
public class SettingController extends BaseController {

	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Autowired
	private IUserPrivacySecurityContract userPrivacySecurityContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserInviteContract userInviteContract;
	
	@Autowired
	private IUserAgentService userService;

	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	@RequestMapping(value = "/setting/contact", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getSettingCompanyInfo() throws Exception {
		SysConfigEntity config = sysConfigContract.findByProperty("name", Const.SETTING_CONTACT_INFO);
		String contactField = "";
		String contactValue = "";
		int privacyStatus = 0;
		UserPrivacySecurityEntity ups = userPrivacySecurityContract.findByProperty("userid", RequestUtils.getCurrent().getUserid());
		if (ups!=null&&ups.getStatus()==1) {
			privacyStatus = 1;
		}
		UserBO user = userAgent.findById(RequestUtils.getCurrent().getUserid());
		int videoStatus = 0;
		if(Tools.isNotNull(user))
			videoStatus = user.getAllowVideoChat();
		
		Map<String,Object> info = new HashMap<>();
		if(Tools.isNotNull(config)) {
			JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
			contactField = (String) ctrl.get("contactField");
			contactValue = (String) ctrl.get("contactValue");
		}
//		int dispatchStatus = 0;
		//user.isWaiter() ? 1 : 0
//		int talent = 0;
		
//		TalentBO talentUser = paidAppointAgent.findByUserId(RequestUtils.getCurrent().getUserid());
//		if(Tools.isNotNull(talentUser)) {
//			talent = 1;
//			dispatchStatus = talentUser.isAccept() ? 1 : 0;
//		}
		
		info.put("contactField", contactField);
		info.put("contactValue", contactValue);
		//隐藏安全开关
		info.put("privacyStatus", privacyStatus);
		//是否允许视频聊天
		info.put("allowVideo", videoStatus);
		//是否允许派单
//		info.put("allowDispatch", dispatchStatus);
		//是否是达人标示
		//info.put("talent", talent);
		
		if (Tools.isNotNull(userInviteContract.findByProperty("userid", RequestUtils.getCurrent().getUserid()))){
			info.put("showInviteStatus", 0);
		}else{
			Date currdate = new Date();
			Calendar ca = Calendar.getInstance();
			ca.setTime(user.getCreateTime());
			ca.add(Calendar.DATE, 3);
			Date date = ca.getTime();
			info.put("showInviteStatus", currdate.before(date)?1:0);
		}
		return ActionResult.success(info);
	}
	
	@RequestMapping(value = "/setting/getAllowVideo", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getAllowVideo() throws Exception {
		UserBO user = userAgent.findById(RequestUtils.getCurrent().getUserid());
		int videoStatus = 0;
		if(Tools.isNotNull(user))
			videoStatus = user.getAllowVideoChat();
		Map<String,Object> info = new HashMap<>();
		info.put("allowVideo", videoStatus);
		info.put("tip", "每分钟" + Const.USER_VIDEO_CHAT_DIAMONDS_COST + "钻,打开视频聊天,增加您的收益。\n *收益结算时,平台会收取" + (100 - sysConfigAgent.vacuate().getServiceRatio()) + "%的技术服务费,具体详情以官方为准");
		return ActionResult.success(info);
	}
	
	@RequestMapping(value = "/setting/setAllowVideo", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult setAllowVideo(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		UserBO user = userAgent.findById(RequestUtils.getCurrent().getUserid());
		if(Tools.isNull(user)) {
			return ActionResult.fail(ErrorCodeEnum.state_error);
		}
		Integer allowVideo = json.getInteger("allowVideo");
		if(Tools.isNull(allowVideo)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		userService.updateVideoChat(RequestUtils.getCurrent().getUserid(), allowVideo);
		return ActionResult.success();
	}
	
	@RequestMapping(value = "/setting/setAllowDispatch", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult setAllowDispatch(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		Integer allowDispatch = json.getInteger("allowDispatch");
		if(Tools.isNull(allowDispatch)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		return ActionResult.success();
	}
	
	@RequestMapping(value = "/setting/saveDispatchType", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult saveDispatchType(@RequestBody String body) throws Exception {
		JSONArray arr = JsonHelper.toJsonArray(body);
		if(Tools.isNull(arr) || arr.size() <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		List<Long> checkTypeIds = new ArrayList<>();
		for(int i=0, size=arr.size(); i<size; i++) {
			JSONObject object = arr.getJSONObject(i);
			Boolean select = object.getBoolean("isSelect");
			if(Tools.isNotNull(select) && select.booleanValue() == true) {
				//记录对应的id
				checkTypeIds.add(object.getLong("id"));
			}
		}
		if(checkTypeIds.size() <= 0) {
			return ActionResult.fail(ErrorCodeEnum.setting_dispatch_need_one_error);
		}
		return ActionResult.success();
	}
}

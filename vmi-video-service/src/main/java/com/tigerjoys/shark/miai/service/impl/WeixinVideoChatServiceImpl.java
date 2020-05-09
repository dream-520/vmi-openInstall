package com.tigerjoys.shark.miai.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.shark.miai.common.enums.PlatformEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.ISendMessageAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserModifyDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.SendSmsTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserPointAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.service.IValidCodeService;
import com.tigerjoys.shark.miai.dto.service.BtnData;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPage;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPageNew;
import com.tigerjoys.shark.miai.enums.DlgAndGoPageEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.service.IVChatTextYXService;
import com.tigerjoys.shark.miai.service.IWeixinVideoChatService;

/**
 * VIP服务类
 * 
 * @author yangjunming
 *
 */
@Service
public class WeixinVideoChatServiceImpl implements IWeixinVideoChatService {

	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IVChatTextYXService vChatTextYXService;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	@Autowired
	private ISendMessageAgent sendMessageAgent;
	
	@Autowired
	private IValidCodeService validCodeService;
	
	@Autowired
	private IUserPointAgent userPointAgent;
	

	public static final String IOSPath = "https://apps.apple.com/cn/app/id1498882755";
	
	public static final String APKPath = "http://cdn.vmi2.liaomeivideo.com/upload/2020/03/23/1584965427982_9003.apk";
	
	
	@Override
	public ActionResult wxDialing(long userId,Integer mobileType) throws Exception {
		
		int osType = RequestUtils.getCurrent().getHeader().getOs_type();
		UserBO user = userAgent.findById(userId);
		if(osType != PlatformEnum.H5.type) {
			//判断是否弹出VIP框
			if (vChatTextYXService.checkShowVIPFragment(user)) {
				return vChatTextYXService.CheckFailVIPReturnDesc();
			}
		}
		
		// 有钻跳下载页或绑定手机号，无钻充值
		if (userDiamondAgent.getDiamondBalance(userId) > 0) {
			String downUrl = "";
			int gotoType = 0;
			if(Tools.isNull(user.getMobile())){
				downUrl = Const.WEB_SITE+"/wx/videoChat/web/weixinBindMobile";
			}else{
				if(Tools.isNull(mobileType)){
					return ActionResult.fail(ErrorCodeEnum.parameter_error);
				}
				if(1 == mobileType){
					downUrl = APKPath;
				}else if(2 == mobileType) {
					downUrl = IOSPath;
				}
				gotoType = 1 ;
			}
			Map<String, Object> dataMap = Maps.newHashMap();
			dataMap.put("downURL",downUrl );
			dataMap.put("gotoType",gotoType );
			return ActionResult.success(dataMap);
		} else {
			return checkFailChargeDiamond();
		}
	}

	
	public ActionResult getMobileCode(String mobile) throws Exception{
		if(Tools.isNotNull(mobile)){
			String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(mobile);
	        boolean isMatch = m.matches();
	        if(!isMatch){
	        	return ActionResult.fail(ErrorCodeEnum.share_vip_moblie_error);
	        }
		}
		ActionResult result = ActionResult.success();
		if(Tools.isNotNull(mobile)){
			result = sendMessageAgent.sendMobileValidCode(mobile, SendSmsTypeEnum.auth_mobile);
		}
		return result;
	}
	
	@Override
	public ActionResult bindMobile(long userId,Integer mobileType,String mobile,String recode) throws Exception {

		if (Tools.isNotNull(mobile)) {
			String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(mobile);
			boolean isMatch = m.matches();
			if (!isMatch) {
				return ActionResult.fail(ErrorCodeEnum.share_vip_moblie_error);
			}
		}
		if (Tools.isNull(recode)) {
			return ActionResult.fail(ErrorCodeEnum.sms_recode_Error);
		}
		if (Tools.isNotNull(mobile) && Tools.isNotNull(recode)) {
			if (!sendMessageAgent.checkCode(mobile, recode)) {
				return ActionResult.fail(AgentErrorCodeEnum.valid_code_error);
			}
			if (Tools.isNull(validCodeService.getValidCode(mobile))) {
				return ActionResult.fail(AgentErrorCodeEnum.valid_code_pass);
			}
			UserBO newUser = userAgent.findByMobile(mobile);
			if (Tools.isNotNull(newUser)) {
				return ActionResult.fail(ErrorCodeEnum.auth_mobile_exist);
			}
			newUser = userAgent.findByUsername(mobile);
			if (Tools.isNotNull(newUser)) {
				return ActionResult.fail(ErrorCodeEnum.auth_mobile_exist);
			}

			UserModifyDto modifyDto = new UserModifyDto();
			modifyDto.setUserid(RequestUtils.getCurrent().getUserid());
			modifyDto.setMobile(mobile);
			userAgent.updateUser(modifyDto);

			// 手机号绑定成功送积分
			userPointAgent.changePointAccount(RequestUtils.getCurrent().getUserid(),
					UserPointAccountLogTypeEnum.bind_mobile.getCode(), 1, mobile,
					UserPointAccountLogTypeEnum.bind_mobile.getDesc());
			String downUrl = "";
			if (1 == mobileType) {
				downUrl = APKPath;
			} else if (2 == mobileType) {
				downUrl = IOSPath;
			}
			Map<String, Object> dataMap = Maps.newHashMap();
			dataMap.put("downURL", downUrl);
			return ActionResult.success(dataMap);
		}
		return ActionResult.fail();
	}
	
	public ActionResult checkFailChargeDiamond()  throws Exception{
		int osType = RequestUtils.getCurrent().getHeader().getOs_type();
		if(osType != PlatformEnum.H5.type) {
			boolean ios = false;
			if(osType == PlatformEnum.ios.type)
				ios = true;
			
			DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
			if(ios) {
				BtnData chargebtn = new BtnData("充值");
				chargebtn.setAndroidPageTag(DlgAndGoPageEnum.iosChargeDiamond.getAndroidPage());
				dlgAndGoPage.setBtnDataList(Arrays.asList(new BtnData("取消"), chargebtn));
			} else {
				//费用不足 充值弹窗
				BtnData chargebtn = new BtnData("充值");
				chargebtn.setAndroidPageTag(DlgAndGoPageEnum.webSingle.getAndroidPage());
				chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(Const.WEB_SITE+"/api/income/zuanList","我的钱包"));
				dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
			}
			SysConfigEntity sysConfig = sysConfigAgent.getSysConfig(com.tigerjoys.shark.miai.agent.constant.Const.HINT_INFO_DIAMOND_NOTENOUGH);
			String hintInfo = "你的钻石不足，快快充值\n享受与大V独处的时光吧！";
			if(Tools.isNotNull(sysConfig)){
				hintInfo = sysConfigAgent.replaceToN(sysConfig.getValue());
			}
			dlgAndGoPage.setHintInfo(hintInfo);
			
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("dlgAndGoPage", dlgAndGoPage);
			return ActionResult.success(dataMap);
		} else {
			return ActionResult.fail(ErrorCodeEnum.balance_no_enough.getCode(), "充值后即可畅看哦~");
		}
	}
	
	

}

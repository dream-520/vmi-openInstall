package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.constant.AlibabaSmsConst;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.SendSmsDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.AlibabaSmsSignEnum;
import com.tigerjoys.shark.miai.agent.enums.SendSmsTypeEnum;
import com.tigerjoys.shark.miai.agent.service.ISendMessageService;
import com.tigerjoys.shark.miai.agent.service.IValidCodeService;
import com.tigerjoys.shark.miai.agent.utils.SmsParam;
import com.tigerjoys.shark.miai.inter.contract.ISendSmsContract;
import com.tigerjoys.shark.miai.inter.entity.SendSmsEntity;

/**
 * 短信服务类
 * @author yangjunming
 *
 */
@Service
public class SendMessageServiceImpl implements ISendMessageService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISendSmsContract sendSmsContract;
	
	@Autowired
	private IValidCodeService validCodeService;
	
	@Override
	public ActionResult sendSms(String[] mobiles, SmsParam param , Long userid , SendSmsTypeEnum type) throws Exception {
		if(mobiles == null || mobiles.length == 0) {
			throw new IllegalArgumentException("手机号码出入为空");
		}
		if(type == null ) {
			throw new IllegalArgumentException("短信类型不能为空");
		}
		
		int succCount = 0 , failCount = 0;
		boolean sendStatus = false;
		String sendMobiles = StringUtils.join(mobiles, ",");
		String alibabaSmsSignName = AlibabaSmsConst.SEND_CONTENT_PREFIX;
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		if(Tools.isNotNull(header)){
			alibabaSmsSignName = AlibabaSmsSignEnum.getByDesc(header.getPackageName());
			if(Tools.isNull(alibabaSmsSignName)){
				alibabaSmsSignName = AlibabaSmsConst.SEND_CONTENT_PREFIX;
			}
		}
		try {
			StringBuilder urlBuffer = new StringBuilder(AlibabaSmsConst.SMS_SOURCE);
			urlBuffer.append(":action=").append(AlibabaSmsConst.SEND_ACTION);
			urlBuffer.append(":mobile=").append(sendMobiles);
			urlBuffer.append(":signName=").append(alibabaSmsSignName);
			urlBuffer.append(":param=").append(param);
			urlBuffer.append(":outId=");
			String content = urlBuffer.toString();
			logger.info("alibaba_sendSms_send:"+content);
			
			
			SendSmsResponse  sendSmsResponse = sendMessage(sendMobiles,type.getCode(),param);
			String result="";
			String message="";
			 if(sendSmsResponse != null) {
				 result=sendSmsResponse.getCode();
				 message=sendSmsResponse.getMessage();
				 logger.info("alibaba_sendSms_result:"+result+";mobiles:"+sendMobiles);
			 }else{
				 logger.info("alibaba_sendSms_result:"+"null"+";mobiles:"+sendMobiles);
			 }
			 
			
			//检测发送是否成功
			if (result.indexOf("OK") > -1) {
				sendStatus = true;
			}
			
			List<SendSmsEntity> smses = new ArrayList<>();
			for (int i = 0; i < mobiles.length; i++) {
				SendSmsEntity sms = new SendSmsEntity();
				sms.setUserid(Tools.longValue(userid));
				sms.setType(type.getCode());
				sms.setContent(content);
				sms.setCreate_time(new Date());
				sms.setMobile(mobiles[i]);
				sms.setReturn_mes(result);
				sms.setSend_name(AlibabaSmsConst.SMS_SOURCE);
				sms.setReturn_msg(message);
				if(sendStatus) {//成功
					sms.setSend_state(0);
					sms.setStatus(1);
				} else {
					sms.setSend_state(1);
					sms.setStatus(0);
				}
				smses.add(sms);
			}

			sendSmsContract.insertAll(smses);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
		//输出结果
		SendSmsDto result = new SendSmsDto();
		if (sendStatus) {
			failCount = 0;
			succCount = mobiles.length;
		} else {
			failCount = mobiles.length;
			succCount = 0;
		}
		result.setFailCount(failCount);
		result.setSuccCount(succCount);
		
		return sendStatus ? ActionResult.success("验证码已发送！") : ActionResult.fail(AgentErrorCodeEnum.msgSendError);
	}
	
	/**
	 * 调用短信发送接口
	 * @param url - 接口地址
	 * @param parameterMap - 请求参数
	 * @return String
	 * @throws ClientException 
	 */
	private SendSmsResponse sendMessage(String mobiles,int type,SmsParam param) throws ClientException{
		String alibabaSmsSignName = AlibabaSmsConst.SEND_CONTENT_PREFIX;
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		if(Tools.isNotNull(header)){
			alibabaSmsSignName = AlibabaSmsSignEnum.getByDesc(header.getPackageName());
			if(Tools.isNull(alibabaSmsSignName)){
				alibabaSmsSignName = AlibabaSmsConst.SEND_CONTENT_PREFIX;
			}
		}
		
		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", AlibabaSmsConst.accessKeyId,
				AlibabaSmsConst.accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", AlibabaSmsConst.product, AlibabaSmsConst.domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 SendSmsRequest request = new SendSmsRequest();
		 request.setMethod(MethodType.POST);
		 request.setPhoneNumbers(mobiles);
		 request.setSignName(alibabaSmsSignName);
		 request.setTemplateCode("SMS_"+type);
		 request.setTemplateParam(param==null?"":param.toJson());
		 SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		 return sendSmsResponse;
	}

	@Override
	public ActionResult sendMobileValidCode(String mobile, SendSmsTypeEnum type) throws Exception {
		if(!Tools.isMobile(mobile)) {
			return ActionResult.fail(AgentErrorCodeEnum.mobile_error);
		}
		//获得验证码
		String validateCode = validCodeService.createValidCode(mobile);
		//非测试环境下发送短信，需要验证发送短信次数
		try {
			if (Const.is_test) {
				return ActionResult.success(validateCode,"验证码："+validateCode);
			}else{
				if(this.todaySendSmsCount(mobile)>AlibabaSmsConst.SEND_CODE_LIMIT){
					return ActionResult.fail(AgentErrorCodeEnum.send_code_out);
				}
				return sendSms(new String[]{mobile}, SmsParam.getSmsParam("code", validateCode), RequestUtils.getCurrent().getUserid(), type);
			}
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		return ActionResult.fail();
	}
	
	/**
	 * 今日发送短信的次数
	 */
	@Override
	public long todaySendSmsCount(String mobile) throws Exception {
		if(!Tools.isPhone(mobile)) {
			throw new IllegalArgumentException("手机号码为空或者错误");
		}
		
		long dayTime = Tools.getDayTime();
		//此处需要判断短信是否超过每日发送的上限
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("mobile", mobile));
		pageModel.addQuery(Restrictions.eq("type", SendSmsTypeEnum.regist.getCode()));
		pageModel.addQuery(Restrictions.ge("create_time", new Date(dayTime)));
		pageModel.addQuery(Restrictions.lt("create_time", new Date(dayTime+Tools.DAY_MILLIS)));
		pageModel.addQuery(Restrictions.eq("status", 1));//每天发送的成功次数不要超过X次
		
		return sendSmsContract.count(pageModel);
	}

	@Override
	public boolean checkCode(String mobile, String validCode){
		String code = validCodeService.getValidCode(mobile);
		if(code == null) {
			return false;
		}
		if (code.trim().equals(validCode)) {
			return true;
		}else{
			return false;
		}
	}
	
}

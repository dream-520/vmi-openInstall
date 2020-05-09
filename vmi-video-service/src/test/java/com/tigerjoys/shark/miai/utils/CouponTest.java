package com.tigerjoys.shark.miai.utils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.shark.miai.common.util.RedPackageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.constant.AlibabaSmsConst;
import com.tigerjoys.shark.miai.agent.enums.SendSmsTypeEnum;


public class CouponTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testTime() throws Exception {
		Date current=new Date(Tools.getDayTime(new Date()));
		long endMillis=Tools.getdate(current, 5).getTime()-1000;
		System.out.println(Tools.getFormatDate(new Date(endMillis), "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	@Test
	public void testT() throws Exception {
		System.out.println(Long.toHexString(10000012L));
	}
	
	
	@Test
	public void testSend() throws Exception {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", AlibabaSmsConst.accessKeyId,
					AlibabaSmsConst.accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", AlibabaSmsConst.product, AlibabaSmsConst.domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			 SendSmsRequest request = new SendSmsRequest();
			 request.setMethod(MethodType.POST);
			 String mobilesStr="18811315513";
			 
			 request.setPhoneNumbers(mobilesStr);
			 request.setSignName(AlibabaSmsConst.SEND_CONTENT_PREFIX);
			 request.setTemplateCode("SMS_"+SendSmsTypeEnum.login_mobile.getCode());
				Map<String,String>outMap=new HashMap<>();
				outMap.put("code", "sfgf");
			 request.setTemplateParam(JsonHelper.toJson(outMap));
			 SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			 if(sendSmsResponse.getCode() != null) {
				System.out.println(sendSmsResponse.getCode()+" :"+sendSmsResponse.getMessage());
			 }else{
				 System.out.println("运行失败");
			 }
	}
	
	
	
}

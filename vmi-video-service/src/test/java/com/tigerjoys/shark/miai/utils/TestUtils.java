package com.tigerjoys.shark.miai.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;

public class TestUtils {
	
	private final static String NO_LOGIN_HEADER = "QHrejHRTQfaOSg6Z4tRYLOyOuR0ejLU8YdV1psYKKxCBtJCO/Ug9OSHcprzx na0C+zoPj0PxKfQIahxICAieJweeyMwpO8dcbeq8WBUeufCdKZ/4w1/3dqpE sqXLRuAgpUY1AihrZdRl/IFGDdxpSFfZTuX5O7jq5OXDtQ2OU/qYsCUwZynt CnQxW98da7sv1g4RAyiWXP90dLdE2psQ7S4T/id+3B+3k74c+hBWfS3OdexN T7wu62UO5P70GDAahVhtaVIr+v9SCRcUsXyj12aDcv9I5hRyF1SPlX1kMKAc jXiSqz6MYeiVtMsaSAxrzfZAu5nxD3Yc68a8+7EL1g==";
	
	private final static String LOGIN_HEADER = "QHrejHRTQfaOSg6Z4tRYLOyOuR0ejLU8YdV1psYKKxCBtJCO/Ug9OSHcprzxna0C+zoPj0PxKfQIahxICAieJweeyMwpO8dcbeq8WBUeufCdKZ/4w1/3dqpEsqXLRuAgpUY1AihrZdRl/IFGDdxpSFfZTuX5O7jq5OXDtQ2OU/qYsCUwZyntCnQxW98da7sv1g4RAyiWXP90dLdE2psQ7S4T/id+3B+3k74c+hBWfS3OdexNT7wu62UO5P70GDAayhix0Mg36UI9F17Wdu0korq+sSSElQ4keLwfulNjAEuUoPNuY4hokRop31yXWf3gL0BY3tV5VufjCIfaRY1dSg==";
	
	/**
	 * 设置登录的BeatContext
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	public static void setLoginBeatContext(HttpServletRequest request , HttpServletResponse response) throws Exception {
		RequestHeader header = JsonHelper.toObject(AESCipher.aesDecryptString(LOGIN_HEADER), RequestHeader.class);
		System.err.println(JsonHelper.toJson(header));
		BeatContext context = new BeatContext();
		context.setRequest(request);
		context.setResponse(response);
		context.setRequestBody(null);
		context.setIslog(false);
		context.setHeader(header);
		context.setHeaderEncrypt(LOGIN_HEADER);
		context.setUserid(header.getUserid());
		
		RequestUtils.bindBeatContextToCurrentThread(context);
	}
	
	/**
	 * 设置未登录的BeatContext
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	public static void setNoLoginBeatContext(HttpServletRequest request , HttpServletResponse response) throws Exception {
		RequestHeader header = JsonHelper.toObject(AESCipher.aesDecryptString(NO_LOGIN_HEADER), RequestHeader.class);
		
		BeatContext context = new BeatContext();
		context.setRequest(request);
		context.setResponse(response);
		context.setIslog(false);
		context.setHeader(header);
		context.setHeaderEncrypt(NO_LOGIN_HEADER);
		context.setUserid(header.getUserid());
		
		RequestUtils.bindBeatContextToCurrentThread(context);
	}
	
	

}

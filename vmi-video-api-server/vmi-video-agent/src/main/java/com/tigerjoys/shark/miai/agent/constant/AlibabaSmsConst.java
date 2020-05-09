package com.tigerjoys.shark.miai.agent.constant;

import com.tigerjoys.nbs.common.ServiceConfig;

/**
 * 短信常量
 * @author yangjunming
 *
 */
public final class AlibabaSmsConst {
	
	/**
	 * 短信渠道
	 */
	public static final String SMS_SOURCE = "阿里云平台";
	
	/**
	 * 短信发送动作指令
	 */
	public static final String SEND_ACTION = "send";
	
	/**
	 * 验证码位数
	 */
	public static final int valid_code_length = 4;
	
	//初始化ascClient需要的几个参数
	public static final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
	
	public static final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
	
	//替换成你的AK
	public static final String accessKeyId = ServiceConfig.getInstance().getString("sms_accessKeyId");//你的accessKeyId,参考本文档步骤2
	
	public static final String accessKeySecret = ServiceConfig.getInstance().getString("sms_accessKeySecret");//你的accessKeySecret，参考本文档步骤2

	
	/**
	 * 同一手机同一天最多申请5次验证码
	 */
	public static final int SEND_CODE_LIMIT = 40;
	
	/**
	 * 短信签名
	 */
	public static final String SEND_CONTENT_PREFIX = "V密";
	

}

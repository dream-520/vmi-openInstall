package com.tigerjoys.shark.miai.agent.service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.agent.enums.SendSmsTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.SmsParam;

/**
 * 短信服务类
 * @author lipeng
 *
 */
public interface ISendMessageService {
	
	/**
	 * 发送短信
	 * @param mobiles - 手机号码
	 * @param param - JSON格式参数
	 * @param userid - 用户ID
	 * @param type - 短信类型
	 * @return ActionResult
	 * @throws Exception
	 */
	public ActionResult sendSms(String[] mobiles, SmsParam param , Long userid , SendSmsTypeEnum type) throws Exception;

	/**
	 * 发送短信
	 * @param mobile - 手机号码
	 * @param type - 短信类型
	 * @return ActionResult
	 */
	public ActionResult sendMobileValidCode(String mobile, SendSmsTypeEnum type) throws Exception;
	
	/**
	 * 指定手机号码在今日发送验证码的次数
	 * @param mobile - 手机号码
	 * @return long
	 * @throws Exception
	 */
	public long todaySendSmsCount(String mobile) throws Exception;
	
	/**
	 * 判断验证码是否正确
	 * @param mobile
	 * @param validCode
	 * @return
	 * @throws Exception
	 */
	public boolean checkCode(String mobile, String validCode);
	
}

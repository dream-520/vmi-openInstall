package com.tigerjoys.shark.miai.agent;

import java.util.Date;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.agent.enums.SendSmsTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.SmsParam;

/**
 * 发送短信代理接口
 * @author chengang
 *
 */
public interface ISendMessageAgent {
	
	/**
	 * 发送短信
	 * @param param - json参数(参考SendSmsTypeEnum类)
	 * @param userid - 用户ID
	 * @param type - 短信类型
	 * @param mobiles - 手机号码
	 * @return ActionResult
	 * @throws Exception
	 */
	public ActionResult sendSms(SmsParam param , Long userid , SendSmsTypeEnum type , String... mobiles) throws Exception;

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
	
	/**
	 * 添加发送短信到延迟队列中，短信将在定时器中定时发送。。对于时效性要求不高的发送需求，可以使用此方法
	 * @param param - 发送参数
	 * @param userid - 用户ID
	 * @param type - 发送枚举类型
	 * @param dealyTime - 延迟时间，为null则不延迟
	 * @param mobiles - 手机号码集合
	 * @return long 队列的ID
	 * @throws Exception
	 */
	public long addSmsDelayQueue(SmsParam param , Long userid , SendSmsTypeEnum type , Date dealyTime , String... mobiles) throws Exception;
	
	/**
	 * 根据队列ID从延迟发送队列中删除。如果短信已经发送出去，则返回false
	 * @param id - ID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delSmsDelayQueue(long id) throws Exception;
	
}

package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 微信H5拨打
 * @author yangjunming
 *
 */
public interface IWeixinVideoChatService {
	
	/**
	 * 获对视频通话对方信息
	 * @param userId    用户拨打
	 * @param mobileType    手机类型
	 * @return
	 * @throws Exception
	 */
	public ActionResult wxDialing(long userId,Integer mobileType) throws Exception;
	
	/**
	 * 获取验证码
	 * @param mobile
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public ActionResult getMobileCode(String mobile) throws Exception;
	
	/**
	 * 绑定手机号
	 * @param userId
	 * @param moblie
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public ActionResult bindMobile(long userId,Integer mobileType,String mobile,String recode) throws Exception;

}

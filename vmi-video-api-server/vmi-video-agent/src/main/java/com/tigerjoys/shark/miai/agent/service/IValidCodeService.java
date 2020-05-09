package com.tigerjoys.shark.miai.agent.service;

import org.springframework.stereotype.Service;

/**
 * 用户验证码
 * @author liepng
 *
 */
@Service
public interface IValidCodeService {
	
	/**
	 * 生成短信验证码
	 * @param mobile - 手机号码
	 * @return String
	 */
	public String createValidCode(String mobile);
	
	/**
	 * 获取短信验证码
	 * @param mobile - 手机号码
	 * @return String
	 */
	public String getValidCode(String mobile);
	
	/**
	 * 删除掉验证码信息
	 * @param mobile - 手机号码
	 */
	public void delValidCode(String mobile);
	
}

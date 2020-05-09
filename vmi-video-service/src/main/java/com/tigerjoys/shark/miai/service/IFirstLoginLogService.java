package com.tigerjoys.shark.miai.service;


/**
 * 用户每日首次登陆接口服务定义
 * @author liuman
 *
 */
public interface IFirstLoginLogService {
	
	/**
	 * 根据用户端请求过来的用户id查询判断今天是否首次登录
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public boolean judgeIsFirstLogin(Long userId) throws Exception;
	
	/**
	 * 首次登录要插入一条日志记录
	 * @param userId 用户id
	 * @throws Exception
	 */
	public void recordFirstLogin(Long userId) throws Exception;
}

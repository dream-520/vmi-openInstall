package com.tigerjoys.shark.miai.service;


/**
 * 用户每日首次分享接口服务定义
 * @author liuman
 *
 */
public interface IFirstShareLogService {
	
	/**
	 * 根据用户端请求过来的用户id查询判断今天是否首次分享
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public boolean judgeIsFirstShare(Long userId) throws Exception;
	
	/**
	 * 首次登录要插入一条日志记录
	 * @param userId 用户id
	 * @throws Exception
	 */
	public void recordFirstShare(Long userId) throws Exception;
}

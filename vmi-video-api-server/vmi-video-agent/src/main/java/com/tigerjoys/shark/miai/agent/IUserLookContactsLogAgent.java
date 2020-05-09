package com.tigerjoys.shark.miai.agent;

/**
 * 用户魅力值服务代理接口
 * @author lipeng
 *
 */
public interface IUserLookContactsLogAgent {
	
	/**
	 * 根据用户id获得今天查看联系方式次数
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int getCount(long userid)throws Exception;
	
	/**
	 * 根据用户ID和被查看用户Id判断今天是否已经查看过改用户的联系方式
	 * @param userid
	 * @param userId
	 * @return true 已查看过 false 没有查看过
	 * @throws Exception
	 */
	public boolean ifLooked(long userid ,long otherid) throws Exception;
	
	/**
	 * 创建查看记录
	 * @param userid
	 * @param userId
	 * @return true 已查看过 false 没有查看过
	 * @throws Exception
	 */
	public void creat(long userid ,long otherid) throws Exception;
	
	
	
	
	
}

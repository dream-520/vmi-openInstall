package com.tigerjoys.shark.miai.agent;

/**
 * 用户魅力值服务代理接口
 * @author lipeng
 *
 */
public interface IUserCharmAgent {
	
	/**
	 * 根据ID获得道具对象，此方法走缓存
	 * @param id - 道具ID
	 * @return UserDto
	 * @throws Exception
	 */
	public Integer getCharmByUserid(long userid) throws Exception;
	
	/**
	 * 根据ID获得用户对象并且刷新缓存
	 * @param id - 道具ID
	 * @return PropBO
	 * @throws Exception
	 */
	public void addUserCharm(long userid) throws Exception;
	
	
}

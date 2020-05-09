package com.tigerjoys.shark.miai.agent;

/**
 * 用户在线列表服务代理接口
 * @author chengang
 *
 */
public interface IUserOnlineListAgent {
	
	/**
	 * 判断用户是否在线
	 * @param userid - 用户ID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean existsOnline(long userid) throws Exception;
	
	/**
	 * 将用户添加到在线列表/用户在线时长列表中，按照时间排列
	 * @param userid - 用户ID
	 * @param clientId - 客户端ID
	 * @throws Exception
	 */
	public void addOnlineUser(long userid , String clientId) throws Exception;
	
	/**
	 * 获得在线用户的客户端ID
	 * @param userid - 用户ID
	 * @return String
	 * @throws Exception
	 */
	public String getOnlineUserClientId(long userid) throws Exception;
	
	/**
	 * 刷新在线列表/用户在线时长列表的时间
	 * @param userid - 用户ID
	 * @param clientId - 客户端ID
	 * @throws Exception
	 */
	public void refreshOnlineUser(long userid , String clientId) throws Exception;
	
	/**
	 * 将用户移出在线列表中
	 * @param userid - 用户ID
	 * @throws Exception
	 */
	public void removeOnlineUser(long userid) throws Exception;
	
	/**
	 * 将5分钟内未刷新的用户移出在线列表
	 * @return long 移除的数量
	 * @throws Exception
	 */
	public long removeExpireUserOnlineList() throws Exception;
	
	/**
	 * 将用户的在线时长保存到日志表中
	 * @throws Exception
	 */
	public void saveUserOnlineTimeLog() throws Exception;
	
}

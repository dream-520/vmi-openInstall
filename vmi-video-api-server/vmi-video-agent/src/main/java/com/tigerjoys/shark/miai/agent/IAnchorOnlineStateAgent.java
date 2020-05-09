package com.tigerjoys.shark.miai.agent;

/**
 * 主播在线列表服务代理接口
 * @author shiming
 */
public interface IAnchorOnlineStateAgent {
	
	/**
	 * 判断用户是否在线
	 * @param userid - 用户ID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean existsOnline(long userid) throws Exception;
	
	/**
	 * 获取主播是否处于在聊状态
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean existsTalking(long userid) throws Exception;

	/**
	 * 刷新在线列表/用户在线时长列表的时间
	 * @param userid - 用户ID
	 * @param clientId - 客户端ID
	 * @throws Exception
	 */
	public void refreshOnlineAnchor(long userid) throws Exception;
	
	/**
	 * 将3分钟内未刷新的主播移出在线列表
	 * @return long 移除的数量
	 * @throws Exception
	 */
	public long removeExpireAnchorOnlineList() throws Exception;
	
	/**
	 * 刷新主播的动作行为
	 * @param userid
	 */
	public void waiterActionOnline(long userid);
	
	/**
	 * 清除30分钟没有动作行为的主播
	 * @return
	 * @throws Exception
	 */
	public long removeExpireAnchorActionOnlineList() throws Exception;
	
}

package com.tigerjoys.shark.miai.agent;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Tuple;

/**
 * 普通用户在线列表服务代理接口
 * @author chengang
 *
 */
public interface IUserOrdinaryOnlineListAgent {
	
	/**
	 * 将用户添加到在线列表/用户在线时长列表中，按照时间排列
	 * @param userid - 用户ID
	 * @param clientId - 客户端ID
	 * @throws Exception
	 */
	public void addOnlineUser(long userid ) throws Exception;
	
	
	/**
	 * 获取count用户列表
	 * @param userid - 用户ID
	 * @param count - 客户端ID
	 * @throws Exception
	 */
	public Set<String> getOnlineUser(int count) throws Exception;
	
	/**
	 * 获取count用户列表
	 * @param userid - 用户ID
	 * @param count - 客户端ID
	 * @throws Exception
	 */
	public Set<String> getOnlineUser(int score ,int count) throws Exception;
	
	/**
	 * 获取count用户列表,包含score
	 * @param userid - 用户ID
	 * @param count - 客户端ID
	 * @throws Exception
	 */
	public Set<Tuple> getOnlineUserWithScores(double score, int count) throws Exception;
	
	/**
	 * 将用户移出在线列表中
	 * @param userid - 用户ID
	 * @throws Exception
	 */
	public void removeOnlineUser() throws Exception;
	
	/**
	 * 添加主播当天拨打列表
	 * @param userid
	 * @throws Exception
	 */
	public void addAnchorDialingUser(long anchorId,long userid) throws Exception;
	
	/**
	 * 获取主播当天拨打列表
	 * @param anchorId
	 * @return
	 * @throws Exception
	 */
	public Set<String> getAnchorDialingUser(long anchorId) throws Exception;
	
	/**
	 * 获取主播当天拨数量
	 * @param anchorId
	 * @return
	 * @throws Exception
	 */
	public long getAnchorDialingUserNum(long anchorId) throws Exception;
	
	/**
	 * 随机获取主播当天用户拨打列表
	 * @param anchorId
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<String> getAnchorDialingRandomUser(long anchorId,int count) throws Exception;
	
	/**
	 * 添加主播当天接听用户列表
	 * @param userid
	 * @throws Exception
	 */
	public void addAnchorDialingRecvUser(long anchorId,long userid) throws Exception;
	
	/**
	 * 获取主播当天接听用户个数
	 * @param anchorId
	 * @return
	 * @throws Exception
	 */
	public long getAnchorDialingRecvUserTotal(long anchorId) throws Exception;
	
	 /**
	  * 设置主播禁止拨打用户
	  * @param userId
	  * @param expire   秒
	  * @throws Exception
	  */
	public void setAnchorForbidDialFlag(long userId,int expire) throws Exception;
	
	/**
	 * 获取主播禁止拨打用户标记
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean getAnchorForbidDialFlag(long userId) throws Exception;
}

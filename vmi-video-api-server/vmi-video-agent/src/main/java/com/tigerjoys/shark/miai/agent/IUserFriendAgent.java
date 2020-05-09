package com.tigerjoys.shark.miai.agent;

import java.util.List;
import java.util.Map;

import com.tigerjoys.shark.miai.agent.dto.UserFriendBO;

/**
 * 用户好友服务代理接口
 * @author chengang
 *
 */
public interface IUserFriendAgent {
	
	/**
	 * 根据用户ID和朋友ID查找关系对象，不使用缓存
	 * @param userid - 用户ID
	 * @param friendid - 好友ID
	 * @return UserFriendBO
	 * @throws Exception
	 */
	public UserFriendBO findUserFriendWithoutCache(long userid, long friendid) throws Exception;
	
	/**
	 * 根据用户ID和朋友ID找到好友关系对象
	 * @param userid - 用户ID
	 * @param friendid - 好友关系
	 * @return UserFriendBO
	 * @throws Exception
	 */
	public UserFriendBO findUserFriend(long userid , long friendid) throws Exception;
	
	
	/**
	 * 根据用户ID和朋友ID判断是否关注好友
	 * @param userid - 用户ID
	 * @param friendid - 好友关系
	 * @return UserFriendBO
	 * @throws Exception
	 */
	public boolean isAttention(long userid , long friendid) throws Exception;
	
	/**
	 * 根据用户ID和朋友ID集合找到好友关系对象
	 * @param userid - 用户ID
	 * @param friendidList - 好友ID集合
	 * @return Map<Long , UserFriendBO> key=friendid , value=UserFriendBO
	 * @throws Exception
	 */
	public Map<Long , UserFriendBO> findUserFriendByFriendIdList(long userid , List<Long> friendidList) throws Exception;
	
	/**
	 * 根据好友ID和用户ID集合找到好友关系对象
	 * @param friendid - 好友ID
	 * @param useridList - 用户ID集合
	 * @return Map<Long , UserFriendBO> key=userid , value=UserFriendBO
	 * @throws Exception
	 */
	public Map<Long , UserFriendBO> findFriendByUserIdList(long friendid , List<Long> useridList) throws Exception;
	
	/**
	 * 分页获得用户的关注+好友列表
	 * @param userid - 用户ID
	 * @param lastUserFrId - 最后一个UserFriend ID，如果第一页则为0
	 * @param pagesize - 获取数量
	 * @return List<UserFriendBO>
	 * @throws Exception
	 */
	public List<UserFriendBO> findUserFriendList(long userid , long lastUserFrId , int pagesize) throws Exception;
	
	/**
	 * 获得用户的关注+好友数量
	 * @param userid - 用户ID
	 * @return long
	 * @throws Exception
	 */
	public long findUserFriendCount(long userid) throws Exception;
	
	/**
	 * 分页获得用户互粉的好友列表
	 * @param userid - 用户ID
	 * @param lastUserFrId - 最后一个UserFriend ID，如果第一页则为0
	 * @param pagesize - 获取数量
	 * @return List<UserFriendBO>
	 * @throws Exception
	 */
	public List<UserFriendBO> findUserFriendPowderList(long userid , long lastUserFrId , int pagesize) throws Exception;
	
	/**
	 * 获得用户互粉的好友数量
	 * @param userid - 用户ID
	 * @return long
	 * @throws Exception
	 */
	public long findUserFriendPowderCount(long userid) throws Exception;
	
	/**
	 * 分页获得我的粉丝列表
	 * @param friendid - 好友ID
	 * @param lastUserFrId - 最后一个UserFriend ID，如果第一页则为0
	 * @param pagesize - 获取数量
	 * @return List<UserFriendBO>
	 * @throws Exception
	 */
	public List<UserFriendBO> findFriendUserList(long friendid , long lastUserFrId , int pagesize) throws Exception;
	
	/**
	 * 获得关注我的用户的好友数量
	 * @param friendid - 好友ID
	 * @return long
	 * @throws Exception
	 */
	public long findFriendUserCount(long friendid) throws Exception;
	
	/**
	 * 添加好友
	 * @param userid - 用户ID
	 * @param friendid - 要添加的好友ID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addFriend(long userid , long friendid) throws Exception;
	
	/**
	 * 移除好友
	 * @param userid - 用户ID
	 * @param friendid - 要移除的好友ID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean removeFriend(long userid , long friendid) throws Exception;
	
	/**
	 * 清空用户的缓存信息
	 * @param userid - 用户ID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean clearUserFriendCache(long userid) throws Exception;
	
	
	/**
	 * 移除与该用户所有的好友关系
	 * @param userid - 用户ID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean removeAllFriend(long userid) throws Exception;

}

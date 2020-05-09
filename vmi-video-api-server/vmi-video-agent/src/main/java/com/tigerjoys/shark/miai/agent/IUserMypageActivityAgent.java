package com.tigerjoys.shark.miai.agent;

import java.util.List;

import com.tigerjoys.shark.miai.inter.entity.UserMypageActivityEntity;

/**
 * 我的页面活动列表
 * @author lipeng
 *
 */
public interface IUserMypageActivityAgent {
	
	/**
	 * 获取我的页面活动标题，此方法走缓存
	 * @param id - 道具ID
	 * @return UserDto
	 * @throws Exception
	 */
	public List<UserMypageActivityEntity> getMypage() throws Exception;
	
	/**
	 * 获取我的页面活动标题，此方法走缓存
	 * @param id - 道具ID
	 * @return UserDto
	 * @throws Exception
	 */
	public void delGetMypageKey() throws Exception;
	
	/**
	 * 获取小红点点亮目录标识Code
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<Long> getIndexCode(Long userid) throws Exception;
	
	/**
	 * 添加活动key
	 * @param userid
	 */
	public void addActivityKey(Long id);
	
	/**
	 * 添加活动key
	 * @param userid
	 */
	public void delActivityKey(Long id);
	
	/**
	 * 添加活动key
	 * @param userid
	 */
	public void addActivityViewLog(Long id,Long userid);
	
	
	
	
	
}

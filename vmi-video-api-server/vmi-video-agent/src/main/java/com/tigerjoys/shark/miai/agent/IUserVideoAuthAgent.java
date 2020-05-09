package com.tigerjoys.shark.miai.agent;

import java.util.List;
import java.util.Map;

import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.agent.dto.UserVideoAuthBO;

/**
 * 用户视频认证信息代理接口类
 * @author chengang
 *
 */
public interface IUserVideoAuthAgent {
	
	/**
	 * 根据ID获得用户视频认证对象，此方法<b>不走缓存</b>
	 * @param id - ID
	 * @return UserVideoAuthBO
	 * @throws Exception
	 */
	public UserVideoAuthBO findById(long id) throws Exception;
	
	/**
	 * 根据用户ID获得用户视频认证对象，此方法走缓存
	 * @param userid - 用户ID
	 * @return UserVideoAuthBO
	 * @throws Exception
	 */
	public UserVideoAuthBO findByUserId(long userid) throws Exception;
	
	/**
	 * 根据用户ID集合获得用户视频认证对象集合
	 * @param userIds - 用户ID集合
	 * @return Map<Long , UserVideoAuthBO>
	 * @throws Exception
	 */
	public Map<Long , UserVideoAuthBO> findByUserId(List<Long> userIds) throws Exception;
	
	/**
	 * 根据用户ID获得用户认证视频对象并且刷新缓存
	 * @param userid - 用户ID
	 * @return UserVideoAuthBO
	 * @throws Exception
	 */
	public UserVideoAuthBO findByUserIdRefreshCache(long userid) throws Exception;
	
	/**
	 * 插入用户认证视频对象
	 * @param auth - UserVideoAuthBO
	 * @throws Exception
	 */
	public void insert(UserVideoAuthBO auth) throws Exception;
	
	/**
	 * 修改用户认证视频对象，请注意，userid和id为必传条件
	 * @param auth - UserVideoAuthBO
	 * @throws Exception
	 */
	public void update(UserVideoAuthBO auth) throws Exception;
	
	/**
	 * 获得用户视频认证的数量
	 * @param pageModel - 查询条件
	 * @return long
	 * @throws Exception
	 */
	public long count(PageModel pageModel) throws Exception;
	
	/**
	 * 获得用户视频认证的列表
	 * @param pageModel - 查询条件
	 * @return List<UserVideoAuthBO>
	 * @throws Exception
	 */
	public List<UserVideoAuthBO> load(PageModel pageModel) throws Exception;
	
	/**
	 * 查看用户视频是否认证
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean ifAuthVideo(long userid) throws Exception;

}

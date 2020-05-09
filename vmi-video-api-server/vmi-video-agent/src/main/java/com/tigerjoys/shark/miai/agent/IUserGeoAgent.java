package com.tigerjoys.shark.miai.agent;

import java.util.List;
import java.util.Map;

import com.tigerjoys.shark.miai.agent.dto.UserGeoBO;

/**
 * 用户位置信息代理接口
 * @author chengang
 *
 */
public interface IUserGeoAgent {
	
	/**
	 * 根据用户ID找到用户位置信息
	 * @param userid - 用户ID
	 * @return UserGeoBO
	 * @throws Exception
	 */
	public UserGeoBO findByUserId(long userid) throws Exception;
	
	/**
	 * 根据用户ID集合获得用户位置信息集合
	 * @param userIds - 用户ID集合
	 * @return Map<Long , UserGeoBO>
	 * @throws Exception
	 */
	public Map<Long , UserGeoBO> findById(List<Long> userIds) throws Exception;
	
	/**
	 * 根据ID获得用户位置信息对象并且刷新缓存
	 * @param userid - 用户ID
	 * @return UserGeoBO
	 * @throws Exception
	 */
	public UserGeoBO findByUserIdRefreshCache(long userid) throws Exception;
	
	/**
	 * 更新用户的在线位置信息
	 * @param userid - 用户ID
	 * @param lng - 经度
	 * @param lat - 纬度
	 * @param city - 城市名称
	 * @param cityCode - 城市编号
	 * @throws Exception
	 */
	public void modifyUserGeo(long userid , double lng , double lat , String city , Integer cityCode) throws Exception;

}

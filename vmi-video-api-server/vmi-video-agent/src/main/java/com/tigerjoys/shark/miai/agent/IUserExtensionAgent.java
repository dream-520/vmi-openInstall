package com.tigerjoys.shark.miai.agent;

import java.util.List;
import java.util.Map;

import com.tigerjoys.shark.miai.agent.dto.UserExtensionBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserExtensionModifyDto;

/**
 * 用户扩展信息代理接口
 * @author chengang
 *
 */
public interface IUserExtensionAgent {
	
	/**
	 * 根据用户ID找到用户扩展信息
	 * @param userid - 用户ID
	 * @return UserExtensionBO
	 * @throws Exception
	 */
	public UserExtensionBO findByUserId(long userid) throws Exception;
	
	/**
	 * 根据用户ID集合获得用户扩展信息集合
	 * @param userIds - 用户ID集合
	 * @return Map<Long , UserExtensionBO>
	 * @throws Exception
	 */
	public Map<Long , UserExtensionBO> findById(List<Long> userIds) throws Exception;
	
	/**
	 * 根据用户ID获得用户扩展信息对象并且刷新缓存
	 * @param userid - 用户ID
	 * @return UserExtensionBO
	 * @throws Exception
	 */
	public UserExtensionBO findByUserIdRefreshCache(long userid) throws Exception;

	/**
	 * 修改用户信息
	 * @param dto - 修改的实体
	 * @throws Exception
	 */
	public void updateUserExtension(UserExtensionModifyDto dto) throws Exception;
	
	
	
}

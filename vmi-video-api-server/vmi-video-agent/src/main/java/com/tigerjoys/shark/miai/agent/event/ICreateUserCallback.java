package com.tigerjoys.shark.miai.agent.event;

import com.tigerjoys.shark.miai.inter.entity.UserEntity;

/**
 * 创建用户的回调函数
 * @author chengang
 *
 */
public interface ICreateUserCallback {
	
	/**
	 * 创建用户的回调
	 * @param user - UserEntity
	 * @throws Exception
	 */
	public void doExecute(UserEntity user) throws Exception;

}

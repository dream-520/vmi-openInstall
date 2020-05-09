package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;

/**
 * 数据库中  用户基础表[t_user]表 接口类
 * @author chengang
 * @Date 2017-04-12 11:26:55
 *
 */
public interface IUserContract extends BaseContract<UserEntity> {
	
	/**
	 * 根据用户名获取对象
	 * @param username - 用户名
	 * @return UserEntity
	 * @throws Exception
	 */
	public UserEntity findByUsername(String username) throws Exception;
	
	/**
	 * 根据用户昵称获取对象
	 * @param nickname - 用户昵称
	 * @return UserEntity
	 * @throws Exception
	 */
	public UserEntity findByNickname(String nickname) throws Exception;
	
	/**
	 * 根据用户的唯一key获取对象
	 * @param uniqueKey - 用户唯一key
	 * @return UserEntity
	 * @throws Exception
	 */
	public UserEntity findByUniqueKey(String uniqueKey) throws Exception;
	
	/**
	 * 根据手机号码获取对象
	 * @param mobile - 手机号码
	 * @return UserEntity
	 * @throws Exception
	 */
	public UserEntity findByMobile(String mobile) throws Exception;
	
}

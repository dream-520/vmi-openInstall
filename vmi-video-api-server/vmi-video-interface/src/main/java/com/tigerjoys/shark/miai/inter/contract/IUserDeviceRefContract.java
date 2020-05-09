package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserDeviceRefEntity;

/**
 * 数据库中  注册设备跟直播用户的关联表[t_user_device_ref]表 接口类
 * @author chengang
 * @Date 2017-04-12 11:43:23
 *
 */
public interface IUserDeviceRefContract extends BaseContract<UserDeviceRefEntity> {
	
	/**
	 * 查找设备和用户的关系
	 * @param deviceid - 设备ID
	 * @param userid - 用户ID
	 * @return UserDeviceRefEntity
	 * @throws Exception
	 */
	public UserDeviceRefEntity findUserDeviceRef(long deviceid , long userid) throws Exception;
	
}

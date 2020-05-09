package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDeviceRefContract;
import com.tigerjoys.shark.miai.inter.entity.UserDeviceRefEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserDeviceRefMapper;

/**
 * 数据库中  注册设备跟直播用户的关联表[t_user_device_ref]表 接口实现类
 * @author chengang
 * @Date 2017-04-12 11:43:23
 *
 */
@Repository
public class UserDeviceRefContractImpl extends AbstractBaseContract<UserDeviceRefEntity , UserDeviceRefMapper> implements IUserDeviceRefContract {

	@Override
	public UserDeviceRefEntity findUserDeviceRef(long deviceid, long userid) throws Exception {
		return mapper.findUserDeviceRef(deviceid, userid);
	}
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IDeviceContract;
import com.tigerjoys.shark.miai.inter.entity.DeviceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.DeviceMapper;

/**
 * 数据库中  设备表[t_device]表 接口实现类
 * @author lipeng
 * @Date 2019-08-08 14:51:52
 *
 */
@Repository
public class DeviceContractImpl extends AbstractBaseContract<DeviceEntity , DeviceMapper> implements IDeviceContract {
	
}

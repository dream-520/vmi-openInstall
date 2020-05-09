package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IDeviceBaseStationContract;
import com.tigerjoys.shark.miai.inter.entity.DeviceBaseStationEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.DeviceBaseStationMapper;

/**
 * 数据库中  设备基站信息[t_device_base_station]表 接口实现类
 * @author yangjunming
 * @Date 2019-08-02 14:07:46
 *
 */
@Repository
public class DeviceBaseStationContractImpl extends AbstractBaseContract<DeviceBaseStationEntity , DeviceBaseStationMapper> implements IDeviceBaseStationContract {
	
}

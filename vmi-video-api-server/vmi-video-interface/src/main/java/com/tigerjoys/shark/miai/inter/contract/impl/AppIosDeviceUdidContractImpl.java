package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppIosDeviceUdidContract;
import com.tigerjoys.shark.miai.inter.entity.AppIosDeviceUdidEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppIosDeviceUdidMapper;

/**
 * 数据库中  ios设备udid[t_app_ios_device_udid]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-23 09:46:08
 *
 */
@Repository
public class AppIosDeviceUdidContractImpl extends AbstractBaseContract<AppIosDeviceUdidEntity , AppIosDeviceUdidMapper> implements IAppIosDeviceUdidContract {
	
}

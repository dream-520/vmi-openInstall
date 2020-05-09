package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppAreaCityContract;
import com.tigerjoys.shark.miai.inter.entity.AppAreaCityEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppAreaCityMapper;

/**
 * 数据库中  城市级别对应表[t_app_area_city]表 接口实现类
 * @author shiming
 * @Date 2019-07-26 14:49:39
 *
 */
@Repository
public class AppAreaCityContractImpl extends AbstractBaseContract<AppAreaCityEntity , AppAreaCityMapper> implements IAppAreaCityContract {
	
}

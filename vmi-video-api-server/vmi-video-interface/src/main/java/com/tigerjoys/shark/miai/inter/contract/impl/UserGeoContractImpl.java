package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserGeoContract;
import com.tigerjoys.shark.miai.inter.entity.UserGeoEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserGeoMapper;

/**
 * 数据库中  用户定位信息表[t_user_geo]表 接口实现类
 * @author chengang
 * @Date 2017-06-02 12:25:24
 *
 */
@Repository
public class UserGeoContractImpl extends AbstractBaseContract<UserGeoEntity , UserGeoMapper> implements IUserGeoContract {
	
}

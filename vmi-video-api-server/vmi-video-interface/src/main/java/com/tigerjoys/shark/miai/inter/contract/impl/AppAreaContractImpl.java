package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppAreaContract;
import com.tigerjoys.shark.miai.inter.entity.AppAreaEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppAreaMapper;

/**
 * 数据库中  城市字典表[t_app_area]表 接口实现类
 * @author chengang
 * @Date 2017-05-08 16:12:55
 *
 */
@Repository
public class AppAreaContractImpl extends AbstractBaseContract<AppAreaEntity , AppAreaMapper> implements IAppAreaContract {
	
}

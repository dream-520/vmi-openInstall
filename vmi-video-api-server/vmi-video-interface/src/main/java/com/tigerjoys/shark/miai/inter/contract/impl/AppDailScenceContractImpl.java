package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppDailScenceContract;
import com.tigerjoys.shark.miai.inter.entity.AppDailScenceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppDailScenceMapper;

/**
 * 数据库中  [t_app_dail_scence]表 接口实现类
 * @author shiming
 * @Date 2019-12-30 11:15:35
 *
 */
@Repository
public class AppDailScenceContractImpl extends AbstractBaseContract<AppDailScenceEntity , AppDailScenceMapper> implements IAppDailScenceContract {
	
}

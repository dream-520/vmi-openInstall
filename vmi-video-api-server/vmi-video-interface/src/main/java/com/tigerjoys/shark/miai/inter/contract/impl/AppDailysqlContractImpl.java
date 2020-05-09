package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppDailysqlContract;
import com.tigerjoys.shark.miai.inter.entity.AppDailysqlEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppDailysqlMapper;

/**
 * 数据库中  每日SQL数据[t_app_dailysql]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-25 15:33:24
 *
 */
@Repository
public class AppDailysqlContractImpl extends AbstractBaseContract<AppDailysqlEntity , AppDailysqlMapper> implements IAppDailysqlContract {
	
}

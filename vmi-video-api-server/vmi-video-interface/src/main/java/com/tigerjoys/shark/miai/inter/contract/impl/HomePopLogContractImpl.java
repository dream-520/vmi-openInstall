package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IHomePopLogContract;
import com.tigerjoys.shark.miai.inter.entity.HomePopLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.HomePopLogMapper;

/**
 * 数据库中  首页弹窗log表[t_home_pop_log]表 接口实现类
 * @author liuman
 * @Date 2017-12-21 18:21:58
 *
 */
@Repository
public class HomePopLogContractImpl extends AbstractBaseContract<HomePopLogEntity , HomePopLogMapper> implements IHomePopLogContract {
	
}

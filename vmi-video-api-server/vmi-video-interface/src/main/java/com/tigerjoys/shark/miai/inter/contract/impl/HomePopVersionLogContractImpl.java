package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IHomePopVersionLogContract;
import com.tigerjoys.shark.miai.inter.entity.HomePopVersionLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.HomePopVersionLogMapper;

/**
 * 数据库中  升级弹窗log表[t_home_pop_version_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-09-03 13:21:47
 *
 */
@Repository
public class HomePopVersionLogContractImpl extends AbstractBaseContract<HomePopVersionLogEntity , HomePopVersionLogMapper> implements IHomePopVersionLogContract {
	
}

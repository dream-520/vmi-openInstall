package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.inter.mapper.SysConfigMapper;

/**
 * 数据库中  系统配置表[t_sys_config]表 接口实现类
 * @author yangjunming
 * @Date 2017-05-06 16:58:50
 *
 */
@Repository
public class SysConfigContractImpl extends RedisCacheContract<SysConfigEntity , SysConfigMapper> implements ISysConfigContract {
	
}

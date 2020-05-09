package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IGlobalBroadcastContract;
import com.tigerjoys.shark.miai.inter.entity.GlobalBroadcastEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.GlobalBroadcastMapper;

/**
 * 数据库中  全局广播[t_global_broadcast]表 接口实现类
 * @author lipeng
 * @Date 2019-01-08 10:35:57
 *
 */
@Repository
public class GlobalBroadcastContractImpl extends AbstractBaseContract<GlobalBroadcastEntity , GlobalBroadcastMapper> implements IGlobalBroadcastContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.LRUCacheContract;
import com.tigerjoys.shark.miai.inter.contract.IAdminContract;
import com.tigerjoys.shark.miai.inter.entity.AdminEntity;
import com.tigerjoys.shark.miai.inter.mapper.AdminMapper;

/**
 * 数据库中  管理员表[t_admin]表 接口实现类
 * @author chengang
 * @Date 2017-04-26 14:46:53
 *
 */
@Repository
public class AdminContractImpl extends LRUCacheContract<AdminEntity , AdminMapper> implements IAdminContract {
	
}

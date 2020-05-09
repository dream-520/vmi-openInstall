package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.LRUCacheContract;
import com.tigerjoys.shark.miai.inter.contract.IAdminRoleContract;
import com.tigerjoys.shark.miai.inter.entity.AdminRoleEntity;
import com.tigerjoys.shark.miai.inter.mapper.AdminRoleMapper;

/**
 * 数据库中  后台角色表[t_admin_role]表 接口实现类
 * @author chengang
 * @Date 2017-04-26 14:46:53
 *
 */
@Repository
public class AdminRoleContractImpl extends LRUCacheContract<AdminRoleEntity , AdminRoleMapper> implements IAdminRoleContract {
	
}

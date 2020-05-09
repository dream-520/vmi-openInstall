package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.LRUCacheContract;
import com.tigerjoys.shark.miai.inter.contract.IAdminMenuContract;
import com.tigerjoys.shark.miai.inter.entity.AdminMenuEntity;
import com.tigerjoys.shark.miai.inter.mapper.AdminMenuMapper;

/**
 * 数据库中  后台按钮表[t_admin_menu]表 接口实现类
 * @author chengang
 * @Date 2017-04-26 14:46:53
 *
 */
@Repository
public class AdminMenuContractImpl extends LRUCacheContract<AdminMenuEntity , AdminMenuMapper> implements IAdminMenuContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAdminLogContract;
import com.tigerjoys.shark.miai.inter.entity.AdminLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AdminLogMapper;

/**
 * 数据库中  后台管理员日志[t_admin_log]表 接口实现类
 * @author chengang
 * @Date 2017-04-26 14:46:53
 *
 */
@Repository
public class AdminLogContractImpl extends AbstractBaseContract<AdminLogEntity , AdminLogMapper> implements IAdminLogContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRootUserRegLogContract;
import com.tigerjoys.shark.miai.inter.entity.RootUserRegLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RootUserRegLogMapper;

/**
 * 数据库中  root用户登录注册信息记录表[t_root_user_reg_log]表 接口实现类
 * @author lipeng
 * @Date 2019-09-11 14:54:35
 *
 */
@Repository
public class RootUserRegLogContractImpl extends AbstractBaseContract<RootUserRegLogEntity , RootUserRegLogMapper> implements IRootUserRegLogContract {
	
}

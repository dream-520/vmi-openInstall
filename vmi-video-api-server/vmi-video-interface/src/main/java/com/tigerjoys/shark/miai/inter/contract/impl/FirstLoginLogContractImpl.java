package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IFirstLoginLogContract;
import com.tigerjoys.shark.miai.inter.entity.FirstLoginLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.FirstLoginLogMapper;

/**
 * 数据库中  用户每日首次登录日志表[t_first_login_log]表 接口实现类
 * @author liuman
 * @Date 2017-06-07 13:59:40
 *
 */
@Repository
public class FirstLoginLogContractImpl extends AbstractBaseContract<FirstLoginLogEntity , FirstLoginLogMapper> implements IFirstLoginLogContract {
	
}

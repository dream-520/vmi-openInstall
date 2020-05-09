package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IFirstShareLogContract;
import com.tigerjoys.shark.miai.inter.entity.FirstShareLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.FirstShareLogMapper;

/**
 * 数据库中  用户每日首次登录日志表[t_first_share_log]表 接口实现类
 * @author liuman
 * @Date 2017-06-07 15:49:06
 *
 */
@Repository
public class FirstShareLogContractImpl extends AbstractBaseContract<FirstShareLogEntity , FirstShareLogMapper> implements IFirstShareLogContract {
	
}

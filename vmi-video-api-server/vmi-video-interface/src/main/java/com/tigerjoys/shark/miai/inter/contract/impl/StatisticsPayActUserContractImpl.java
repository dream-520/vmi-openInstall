package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsPayActUserContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsPayActUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsPayActUserMapper;

/**
 * 数据库中  付费用户活跃表[t_statistics_pay_act_user]表 接口实现类
 * @author lipeng
 * @Date 2019-11-27 19:56:54
 *
 */
@Repository
public class StatisticsPayActUserContractImpl extends AbstractBaseContract<StatisticsPayActUserEntity , StatisticsPayActUserMapper> implements IStatisticsPayActUserContract {
	
}

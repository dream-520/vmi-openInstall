package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IWorldCupBetLogContract;
import com.tigerjoys.shark.miai.inter.entity.WorldCupBetLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.WorldCupBetLogMapper;

/**
 * 数据库中  世界杯比赛竞猜记录[t_world_cup_bet_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-06-15 13:44:51
 *
 */
@Repository
public class WorldCupBetLogContractImpl extends AbstractBaseContract<WorldCupBetLogEntity , WorldCupBetLogMapper> implements IWorldCupBetLogContract {
	
}

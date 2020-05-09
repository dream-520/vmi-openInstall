package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IWorldCupGameContract;
import com.tigerjoys.shark.miai.inter.entity.WorldCupGameEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.WorldCupGameMapper;

/**
 * 数据库中  世界杯比赛[t_world_cup_game]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-06-15 13:44:51
 *
 */
@Repository
public class WorldCupGameContractImpl extends AbstractBaseContract<WorldCupGameEntity , WorldCupGameMapper> implements IWorldCupGameContract {
	
}

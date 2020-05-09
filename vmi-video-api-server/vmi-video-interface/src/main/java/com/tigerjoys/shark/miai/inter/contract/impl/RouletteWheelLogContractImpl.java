package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRouletteWheelLogContract;
import com.tigerjoys.shark.miai.inter.entity.RouletteWheelLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RouletteWheelLogMapper;

/**
 * 数据库中  轮盘抽奖记录[t_roulette_wheel_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-07-25 16:40:39
 *
 */
@Repository
public class RouletteWheelLogContractImpl extends AbstractBaseContract<RouletteWheelLogEntity , RouletteWheelLogMapper> implements IRouletteWheelLogContract {
	
}

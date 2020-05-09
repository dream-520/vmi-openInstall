package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IFateWheelLogContract;
import com.tigerjoys.shark.miai.inter.entity.FateWheelLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.FateWheelLogMapper;

/**
 * 数据库中  姻缘碰碰抽奖记录[t_fate_wheel_log]表 接口实现类
 * @author shiming
 * @Date 2018-08-08 14:07:04
 *
 */
@Repository
public class FateWheelLogContractImpl extends AbstractBaseContract<FateWheelLogEntity , FateWheelLogMapper> implements IFateWheelLogContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ISlotMachineLogContract;
import com.tigerjoys.shark.miai.inter.entity.SlotMachineLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.SlotMachineLogMapper;

/**
 * 数据库中  老虎机抽奖记录[t_slot_machine_log]表 接口实现类
 * @author shiming
 * @Date 2019-10-30 17:45:01
 *
 */
@Repository
public class SlotMachineLogContractImpl extends AbstractBaseContract<SlotMachineLogEntity , SlotMachineLogMapper> implements ISlotMachineLogContract {
	
}

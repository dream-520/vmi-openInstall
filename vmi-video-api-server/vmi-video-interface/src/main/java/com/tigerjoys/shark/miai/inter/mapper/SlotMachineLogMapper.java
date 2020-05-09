package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.SlotMachineLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  老虎机抽奖记录[t_slot_machine_log]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-10-30 17:45:01
 *
 */
@Producer(entityType=SlotMachineLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface SlotMachineLogMapper extends BaseMapper<SlotMachineLogEntity> {
    
}
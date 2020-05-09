package com.tigerjoys.shark.miai.inter.contract.impl;

import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IEnergyOrderContract;
import com.tigerjoys.shark.miai.inter.entity.EnergyOrderEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.EnergyOrderMapper;

/**
 * 数据库中  充值订单表[t_energy_order]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-16 10:41:55
 *
 */
@Repository
public class EnergyOrderContractImpl extends AbstractBaseContract<EnergyOrderEntity , EnergyOrderMapper> implements IEnergyOrderContract {

  @Override
  public void insert(EnergyOrderEntity energyOrderEntity) throws Exception {
    energyOrderEntity.setOrder_id(IdGenerater.generateId());
    mapper.insert(energyOrderEntity);
  }
}

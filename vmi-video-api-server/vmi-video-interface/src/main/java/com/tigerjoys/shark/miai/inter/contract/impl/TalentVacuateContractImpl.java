package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ITalentVacuateContract;
import com.tigerjoys.shark.miai.inter.entity.TalentVacuateEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.TalentVacuateMapper;

/**
 * 数据库中  达人分成比例表[t_talent_vacuate]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-15 15:01:09
 *
 */
@Repository
public class TalentVacuateContractImpl extends AbstractBaseContract<TalentVacuateEntity , TalentVacuateMapper> implements ITalentVacuateContract {
	
}

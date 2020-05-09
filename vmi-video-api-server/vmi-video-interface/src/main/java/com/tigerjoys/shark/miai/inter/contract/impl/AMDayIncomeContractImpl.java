package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAMDayIncomeContract;
import com.tigerjoys.shark.miai.inter.entity.AMDayIncomeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AMDayIncomeMapper;

/**
 * 数据库中  [t_a_m_day_income]表 接口实现类
 * @author shiming
 * @Date 2018-11-15 17:19:33
 *
 */
@Repository
public class AMDayIncomeContractImpl extends AbstractBaseContract<AMDayIncomeEntity , AMDayIncomeMapper> implements IAMDayIncomeContract {
	
}

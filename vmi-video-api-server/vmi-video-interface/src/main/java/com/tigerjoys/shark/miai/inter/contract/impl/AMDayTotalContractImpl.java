package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAMDayTotalContract;
import com.tigerjoys.shark.miai.inter.entity.AMDayTotalEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AMDayTotalMapper;

/**
 * 数据库中  [t_a_m_day_total]表 接口实现类
 * @author shiming
 * @Date 2018-11-15 14:03:19
 *
 */
@Repository
public class AMDayTotalContractImpl extends AbstractBaseContract<AMDayTotalEntity , AMDayTotalMapper> implements IAMDayTotalContract {
	
}

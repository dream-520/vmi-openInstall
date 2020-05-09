package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStatisticsLaborAnchorTransContract;
import com.tigerjoys.shark.miai.inter.entity.StatisticsLaborAnchorTransEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StatisticsLaborAnchorTransMapper;

/**
 * 数据库中  工会主播汇总统计[t_statistics_labor_anchor_trans]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-06 16:33:01
 *
 */
@Repository
public class StatisticsLaborAnchorTransContractImpl extends AbstractBaseContract<StatisticsLaborAnchorTransEntity , StatisticsLaborAnchorTransMapper> implements IStatisticsLaborAnchorTransContract {
	
}

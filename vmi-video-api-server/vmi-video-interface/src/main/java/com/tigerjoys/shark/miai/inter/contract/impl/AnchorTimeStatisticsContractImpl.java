package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorTimeStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorTimeStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorTimeStatisticsMapper;

/**
 * 数据库中  主播每日在线统计表[t_anchor_time_statistics]表 接口实现类
 * @author shiming
 * @Date 2019-04-16 14:43:20
 *
 */
@Repository
public class AnchorTimeStatisticsContractImpl extends AbstractBaseContract<AnchorTimeStatisticsEntity , AnchorTimeStatisticsMapper> implements IAnchorTimeStatisticsContract {
	
}

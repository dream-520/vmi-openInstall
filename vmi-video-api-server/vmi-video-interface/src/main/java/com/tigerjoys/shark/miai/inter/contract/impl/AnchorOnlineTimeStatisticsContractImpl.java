package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineTimeStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineTimeStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorOnlineTimeStatisticsMapper;

/**
 * 数据库中  主播在线情况统计表[t_anchor_online_time_statistics]表 接口实现类
 * @author shiming
 * @Date 2019-09-02 16:39:45
 *
 */
@Repository
public class AnchorOnlineTimeStatisticsContractImpl extends AbstractBaseContract<AnchorOnlineTimeStatisticsEntity , AnchorOnlineTimeStatisticsMapper> implements IAnchorOnlineTimeStatisticsContract {
	
}

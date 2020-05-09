package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorIntimateRankingsFakeContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsFakeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorIntimateRankingsFakeMapper;

/**
 * 数据库中  主播亲密排行榜假数据[t_anchor_intimate_rankings_fake]表 接口实现类
 * @author lipeng
 * @Date 2019-07-18 17:17:57
 *
 */
@Repository
public class AnchorIntimateRankingsFakeContractImpl extends AbstractBaseContract<AnchorIntimateRankingsFakeEntity , AnchorIntimateRankingsFakeMapper> implements IAnchorIntimateRankingsFakeContract {
	
}

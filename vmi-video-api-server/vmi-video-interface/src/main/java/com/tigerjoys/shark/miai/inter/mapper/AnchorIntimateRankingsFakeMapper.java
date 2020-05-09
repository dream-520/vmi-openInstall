package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsFakeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播亲密排行榜假数据[t_anchor_intimate_rankings_fake]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-07-18 17:17:57
 *
 */
@Producer(entityType=AnchorIntimateRankingsFakeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorIntimateRankingsFakeMapper extends BaseMapper<AnchorIntimateRankingsFakeEntity> {
    
}
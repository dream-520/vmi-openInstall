package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播亲密排行榜[t_anchor_intimate_rankings]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2018-10-30 10:16:48
 *
 */
@Producer(entityType=AnchorIntimateRankingsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorIntimateRankingsMapper extends BaseMapper<AnchorIntimateRankingsEntity> {
    
}
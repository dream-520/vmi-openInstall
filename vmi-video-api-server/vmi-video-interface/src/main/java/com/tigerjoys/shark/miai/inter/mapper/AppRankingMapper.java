package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppRankingEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  App假排行榜数据[t_app_ranking]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-16 18:21:38
 *
 */
@Producer(entityType=AppRankingEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppRankingMapper extends BaseMapper<AppRankingEntity> {
    
}
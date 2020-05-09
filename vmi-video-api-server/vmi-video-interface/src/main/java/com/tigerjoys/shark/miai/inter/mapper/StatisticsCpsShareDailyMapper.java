package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsCpsShareDailyEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  蜜聊CPS每日邀请统计[t_statistics_cps_share_daily]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-10-23 19:46:51
 *
 */
@Producer(entityType=StatisticsCpsShareDailyEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsCpsShareDailyMapper extends BaseMapper<StatisticsCpsShareDailyEntity> {
    
}
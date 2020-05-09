package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserCallAnchorCheckEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户拨打主播统计[t_statistics_user_call_anchor_check]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-06 16:48:30
 *
 */
@Producer(entityType=StatisticsUserCallAnchorCheckEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsUserCallAnchorCheckMapper extends BaseMapper<StatisticsUserCallAnchorCheckEntity> {
    
}
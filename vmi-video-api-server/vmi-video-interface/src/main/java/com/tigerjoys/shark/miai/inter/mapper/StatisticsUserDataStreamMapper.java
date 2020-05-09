package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserDataStreamEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户数据流统计[t_statistics_user_data_stream]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-04-12 10:38:32
 *
 */
@Producer(entityType=StatisticsUserDataStreamEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsUserDataStreamMapper extends BaseMapper<StatisticsUserDataStreamEntity> {
    
}
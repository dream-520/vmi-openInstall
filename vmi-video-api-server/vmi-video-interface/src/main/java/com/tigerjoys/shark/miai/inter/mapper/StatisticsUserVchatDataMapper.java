package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsUserVchatDataEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户聊天数据统计[t_statistics_user_vchat_data]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-07-02 17:27:56
 *
 */
@Producer(entityType=StatisticsUserVchatDataEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StatisticsUserVchatDataMapper extends BaseMapper<StatisticsUserVchatDataEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.DownloadStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_download_statistics]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2018-05-03 17:55:00
 *
 */
@Producer(entityType=DownloadStatisticsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface DownloadStatisticsMapper extends BaseMapper<DownloadStatisticsEntity> {
    
}
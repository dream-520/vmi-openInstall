package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppDownloadTimesEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_app_download_times]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-12-11 16:57:51
 *
 */
@Producer(entityType=AppDownloadTimesEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppDownloadTimesMapper extends BaseMapper<AppDownloadTimesEntity> {
    
}
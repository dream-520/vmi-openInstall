package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppLabelEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  app使用的标签库(形象标签和用户评论标签)[t_app_label]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-10-29 11:27:03
 *
 */
@Producer(entityType=AppLabelEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppLabelMapper extends BaseMapper<AppLabelEntity> {
    
}
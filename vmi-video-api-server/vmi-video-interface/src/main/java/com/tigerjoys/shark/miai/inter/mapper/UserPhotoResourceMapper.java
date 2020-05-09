package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  动态资源表[t_user_photo_resource]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Producer(entityType=UserPhotoResourceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserPhotoResourceMapper extends BaseMapper<UserPhotoResourceEntity> {
    
}
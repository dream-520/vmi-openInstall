package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserVideoAuthEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户视频认证提交表[t_user_video_auth]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-09-19 10:03:42
 *
 */
@Producer(entityType=UserVideoAuthEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserVideoAuthMapper extends BaseMapper<UserVideoAuthEntity> {
    
}
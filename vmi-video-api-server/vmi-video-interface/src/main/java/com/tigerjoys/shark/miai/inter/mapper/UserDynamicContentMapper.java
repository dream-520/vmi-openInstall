package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicContentEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  动态内容表[t_user_dynamic_content]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Producer(entityType=UserDynamicContentEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserDynamicContentMapper extends BaseMapper<UserDynamicContentEntity> {
    
}
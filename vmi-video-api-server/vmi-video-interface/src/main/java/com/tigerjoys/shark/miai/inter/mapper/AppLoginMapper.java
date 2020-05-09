package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppLoginEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  第三方登录表[t_app_login]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-05-06 10:28:58
 *
 */
@Producer(entityType=AppLoginEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppLoginMapper extends BaseMapper<AppLoginEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AdminRoleEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  后台角色表[t_admin_role]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-04-26 14:46:53
 *
 */
@Producer(entityType=AdminRoleEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRoleEntity> {
    
}
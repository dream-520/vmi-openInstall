package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AdminRoleMenuEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  角色跟按钮的关联表[t_admin_role_menu]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-04-26 14:46:53
 *
 */
@Producer(entityType=AdminRoleMenuEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AdminRoleMenuMapper extends BaseMapper<AdminRoleMenuEntity> {
    
}
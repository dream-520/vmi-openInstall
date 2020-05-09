package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserAutoDialForbiddenEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户自动拨打禁用Id[t_user_auto_dial_forbidden]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2020-02-26 15:14:00
 *
 */
@Producer(entityType=UserAutoDialForbiddenEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserAutoDialForbiddenMapper extends BaseMapper<UserAutoDialForbiddenEntity> {
    
}
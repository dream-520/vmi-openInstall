package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserLookContactsLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户查看联系方式记录表[t_user_look_contacts_log]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-11-14 16:58:32
 *
 */
@Producer(entityType=UserLookContactsLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserLookContactsLogMapper extends BaseMapper<UserLookContactsLogEntity> {
    
}
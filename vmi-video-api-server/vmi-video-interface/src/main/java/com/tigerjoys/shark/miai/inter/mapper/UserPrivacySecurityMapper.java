package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserPrivacySecurityEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户隐私安全表[t_user_privacy_security]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-12-25 10:26:14
 *
 */
@Producer(entityType=UserPrivacySecurityEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserPrivacySecurityMapper extends BaseMapper<UserPrivacySecurityEntity> {
    
}
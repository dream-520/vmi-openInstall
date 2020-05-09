package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.IosUserLoginSmsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  IOS提审账号固定验证码[t_ios_user_login_sms]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2020-02-21 17:51:12
 *
 */
@Producer(entityType=IosUserLoginSmsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface IosUserLoginSmsMapper extends BaseMapper<IosUserLoginSmsEntity> {
    
}
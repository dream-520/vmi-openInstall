package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppPayVipUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用于标识首次购买vip后用户是否发送完对应消息的统计表[t_app_pay_vip_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2020-02-14 15:09:15
 *
 */
@Producer(entityType=AppPayVipUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppPayVipUserMapper extends BaseMapper<AppPayVipUserEntity> {
    
}
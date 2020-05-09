package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppPayVipUserAnchorEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户购买vip后对应主播发送联系方式的记录表[t_app_pay_vip_user_anchor]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2020-02-14 15:09:15
 *
 */
@Producer(entityType=AppPayVipUserAnchorEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppPayVipUserAnchorMapper extends BaseMapper<AppPayVipUserAnchorEntity> {
    
}
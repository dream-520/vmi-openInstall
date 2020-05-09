package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteOrderEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  场地订单表[t_appoint_site_order]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-12-14 17:48:58
 *
 */
@Producer(entityType=AppointSiteOrderEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface AppointSiteOrderMapper extends BaseMapper<AppointSiteOrderEntity> {
    
}
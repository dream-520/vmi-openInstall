package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ThirdPartySpreadEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  三方广告推荐注册的设备和用户信息[t_third_party_spread]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2020-01-03 16:31:35
 *
 */
@Producer(entityType=ThirdPartySpreadEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ThirdPartySpreadMapper extends BaseMapper<ThirdPartySpreadEntity> {
    
}
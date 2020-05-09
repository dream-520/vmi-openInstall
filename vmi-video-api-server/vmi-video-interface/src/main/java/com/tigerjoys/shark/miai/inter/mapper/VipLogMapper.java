package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.VipLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户VIP充值记录[t_vip_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
@Producer(entityType=VipLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface VipLogMapper extends BaseMapper<VipLogEntity> {
    
}
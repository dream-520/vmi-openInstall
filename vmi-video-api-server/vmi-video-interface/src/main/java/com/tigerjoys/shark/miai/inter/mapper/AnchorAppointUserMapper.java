package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorAppointUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户约会信息记录表[t_anchor_appoint_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2020-01-07 20:00:05
 *
 */
@Producer(entityType=AnchorAppointUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorAppointUserMapper extends BaseMapper<AnchorAppointUserEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.VchatChannelEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  网易通话信息记录表[t_vchat_channel]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-06-21 14:39:20
 *
 */
@Producer(entityType=VchatChannelEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface VchatChannelMapper extends BaseMapper<VchatChannelEntity> {
    
}
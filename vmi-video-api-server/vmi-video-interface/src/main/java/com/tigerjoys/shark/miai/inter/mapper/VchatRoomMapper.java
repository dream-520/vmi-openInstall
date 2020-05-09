package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_vchat_room]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-18 15:18:56
 *
 */
@Producer(entityType=VchatRoomEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface VchatRoomMapper extends BaseMapper<VchatRoomEntity> {
    
}
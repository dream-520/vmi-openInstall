package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorRecvUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播接听用户[t_anchor_recv_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-05 16:00:33
 *
 */
@Producer(entityType=AnchorRecvUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorRecvUserMapper extends BaseMapper<AnchorRecvUserEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorAppointEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播约会表[t_anchor_appoint]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2020-01-07 20:00:05
 *
 */
@Producer(entityType=AnchorAppointEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorAppointMapper extends BaseMapper<AnchorAppointEntity> {
    
}
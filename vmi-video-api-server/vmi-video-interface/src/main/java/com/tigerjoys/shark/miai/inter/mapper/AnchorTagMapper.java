package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorTagEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播标签表[t_anchor_tag]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-04 16:38:20
 *
 */
@Producer(entityType=AnchorTagEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface AnchorTagMapper extends BaseMapper<AnchorTagEntity> {
    
}
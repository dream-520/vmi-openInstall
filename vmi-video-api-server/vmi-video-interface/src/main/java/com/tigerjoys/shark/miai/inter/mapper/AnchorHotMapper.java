package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorHotEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  ios推荐主播类别表[t_anchor_hot]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-03-25 15:25:10
 *
 */
@Producer(entityType=AnchorHotEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorHotMapper extends BaseMapper<AnchorHotEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorLevelCheckEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播等级考核表[t_anchor_level_check]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-08-27 14:33:50
 *
 */
@Producer(entityType=AnchorLevelCheckEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorLevelCheckMapper extends BaseMapper<AnchorLevelCheckEntity> {
    
}
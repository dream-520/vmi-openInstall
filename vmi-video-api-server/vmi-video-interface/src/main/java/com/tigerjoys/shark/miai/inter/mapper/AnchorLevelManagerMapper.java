package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorLevelManagerEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播升级体系[t_anchor_level_manager]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-08-27 11:24:01
 *
 */
@Producer(entityType=AnchorLevelManagerEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorLevelManagerMapper extends BaseMapper<AnchorLevelManagerEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_short_video]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-01-02 15:45:57
 *
 */
@Producer(entityType=ShortVideoEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ShortVideoMapper extends BaseMapper<ShortVideoEntity> {
    
}
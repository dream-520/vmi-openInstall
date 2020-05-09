package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.TopHeadInfoEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_top_head_info]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2018-03-09 18:19:37
 *
 */
@Producer(entityType=TopHeadInfoEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface TopHeadInfoMapper extends BaseMapper<TopHeadInfoEntity> {
    
}
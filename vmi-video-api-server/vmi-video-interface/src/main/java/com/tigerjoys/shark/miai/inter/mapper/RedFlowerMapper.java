package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  小红花价格列表[t_red_flower]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-12-20 14:44:38
 *
 */
@Producer(entityType=RedFlowerEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface RedFlowerMapper extends BaseMapper<RedFlowerEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.BSequenceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_b_sequence]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2018-09-18 11:24:01
 *
 */
@Producer(entityType=BSequenceEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface BSequenceMapper extends BaseMapper<BSequenceEntity> {
    
}
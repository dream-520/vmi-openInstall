package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AaPEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_aa_p]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-11-01 18:58:30
 *
 */
@Producer(entityType=AaPEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AaPMapper extends BaseMapper<AaPEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.VPageEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_v_page]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Producer(entityType=VPageEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface VPageMapper extends BaseMapper<VPageEntity> {
    
}
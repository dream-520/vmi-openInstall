package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorDeductEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_anchor_deduct]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-20 19:28:15
 *
 */
@Producer(entityType=AnchorDeductEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorDeductMapper extends BaseMapper<AnchorDeductEntity> {
    
}
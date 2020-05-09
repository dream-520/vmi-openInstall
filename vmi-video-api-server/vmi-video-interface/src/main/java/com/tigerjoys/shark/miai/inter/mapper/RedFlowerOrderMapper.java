package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerOrderEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  小红花订单表[t_red_flower_order]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-08-16 12:39:09
 *
 */
@Producer(entityType=RedFlowerOrderEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface RedFlowerOrderMapper extends BaseMapper<RedFlowerOrderEntity> {
    
}
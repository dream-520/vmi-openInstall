package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorUserGreetEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  记录用户打过招呼的主播数据[t_anchor_user_greet]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2020-01-08 15:15:13
 *
 */
@Producer(entityType=AnchorUserGreetEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorUserGreetMapper extends BaseMapper<AnchorUserGreetEntity> {
    
}
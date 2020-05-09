package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorShutEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播下架记录[t_anchor_shut]表 dao通用操作接口实现类
 * @author shiming
<<<<<<< HEAD
 * @Date 2019-08-29 19:59:39
=======
 * @Date 2019-08-29 19:54:23
>>>>>>> vmi0821
 *
 */
@Producer(entityType=AnchorShutEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorShutMapper extends BaseMapper<AnchorShutEntity> {
    
}
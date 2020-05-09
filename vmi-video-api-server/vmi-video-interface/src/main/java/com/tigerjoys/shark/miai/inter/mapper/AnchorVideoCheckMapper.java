package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorVideoCheckEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  通话监黄业务处理表[t_anchor_video_check]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-08-23 15:12:48
 *
 */
@Producer(entityType=AnchorVideoCheckEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorVideoCheckMapper extends BaseMapper<AnchorVideoCheckEntity> {
    
}
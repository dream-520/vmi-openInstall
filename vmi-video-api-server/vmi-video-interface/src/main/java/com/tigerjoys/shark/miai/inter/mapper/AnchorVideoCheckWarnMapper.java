package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorVideoCheckWarnEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  监黄业务警告表[t_anchor_video_check_warn]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-08-23 17:57:18
 *
 */
@Producer(entityType=AnchorVideoCheckWarnEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorVideoCheckWarnMapper extends BaseMapper<AnchorVideoCheckWarnEntity> {
    
}
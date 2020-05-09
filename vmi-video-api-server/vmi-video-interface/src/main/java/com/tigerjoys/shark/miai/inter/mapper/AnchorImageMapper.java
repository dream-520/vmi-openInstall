package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorImageEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播个人主页形象图片[t_anchor_image]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-03 19:24:27
 *
 */
@Producer(entityType=AnchorImageEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorImageMapper extends BaseMapper<AnchorImageEntity> {
    
}
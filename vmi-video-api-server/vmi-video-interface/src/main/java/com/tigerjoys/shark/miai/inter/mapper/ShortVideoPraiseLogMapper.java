package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoPraiseLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_short_video_praise_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2018-10-29 17:51:26
 *
 */
@Producer(entityType=ShortVideoPraiseLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ShortVideoPraiseLogMapper extends BaseMapper<ShortVideoPraiseLogEntity> {
    
}
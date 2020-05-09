package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorAudioEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播音频列表数据[t_anchor_audio]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-08-06 19:46:56
 *
 */
@Producer(entityType=AnchorAudioEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorAudioMapper extends BaseMapper<AnchorAudioEntity> {
    
}
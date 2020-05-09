package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoTranscodeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_short_video_transcode]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-04-09 18:50:14
 *
 */
@Producer(entityType=ShortVideoTranscodeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ShortVideoTranscodeMapper extends BaseMapper<ShortVideoTranscodeEntity> {
    
}
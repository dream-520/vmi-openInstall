package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoWatchDayLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  短视频每日观看数[t_short_video_watch_day_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-01-02 15:45:58
 *
 */
@Producer(entityType=ShortVideoWatchDayLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ShortVideoWatchDayLogMapper extends BaseMapper<ShortVideoWatchDayLogEntity> {
    
}
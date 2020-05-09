package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserVideoChatRecordLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户视频会话记录流水（每分钟计一次）[t_user_video_chat_record_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:25
 *
 */
@Producer(entityType=UserVideoChatRecordLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserVideoChatRecordLogMapper extends BaseMapper<UserVideoChatRecordLogEntity> {
    
}
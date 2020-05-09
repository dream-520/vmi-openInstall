package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  1分钟兔费聊体验日志[t_free_video_chat_experience_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-12-06 16:14:10
 *
 */
@Producer(entityType=FreeVideoChatExperienceLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface FreeVideoChatExperienceLogMapper extends BaseMapper<FreeVideoChatExperienceLogEntity> {
    
}
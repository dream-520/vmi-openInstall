package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  1分钟兔费聊体验[t_free_video_chat_experience]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-17 10:14:37
 *
 */
@Producer(entityType=FreeVideoChatExperienceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface FreeVideoChatExperienceMapper extends BaseMapper<FreeVideoChatExperienceEntity> {
    
}
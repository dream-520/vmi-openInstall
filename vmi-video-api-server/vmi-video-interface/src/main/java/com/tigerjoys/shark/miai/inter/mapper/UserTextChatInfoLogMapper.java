package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatInfoLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户文字聊天详细记录[t_user_text_chat_info_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-08-26 11:23:17
 *
 */
@Producer(entityType=UserTextChatInfoLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserTextChatInfoLogMapper extends BaseMapper<UserTextChatInfoLogEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatHistoryEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  文字聊天所有记录[t_user_text_chat_history]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-10-08 19:47:06
 *
 */
@Producer(entityType=UserTextChatHistoryEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserTextChatHistoryMapper extends BaseMapper<UserTextChatHistoryEntity> {
    
}
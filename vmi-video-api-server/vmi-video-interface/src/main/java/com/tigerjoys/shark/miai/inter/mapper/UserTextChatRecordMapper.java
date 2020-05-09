package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatRecordEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户图文聊天记录[t_user_text_chat_record]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-21 15:03:18
 *
 */
@Producer(entityType=UserTextChatRecordEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserTextChatRecordMapper extends BaseMapper<UserTextChatRecordEntity> {
    
}
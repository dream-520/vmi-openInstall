package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CopyUserTextChatInfoLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户文字聊天详细记录[t_copy_user_text_chat_info_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-12-17 17:04:13
 *
 */
@Producer(entityType=CopyUserTextChatInfoLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface CopyUserTextChatInfoLogMapper extends BaseMapper<CopyUserTextChatInfoLogEntity> {
    
}
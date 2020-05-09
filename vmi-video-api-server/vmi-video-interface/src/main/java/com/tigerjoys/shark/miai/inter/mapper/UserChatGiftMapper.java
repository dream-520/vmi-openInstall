package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  礼物列表[t_user_chat_gift]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-07-24 12:01:43
 *
 */
@Producer(entityType=UserChatGiftEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserChatGiftMapper extends BaseMapper<UserChatGiftEntity> {
    
}
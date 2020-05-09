package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftLogFakeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  礼物消费记录[t_user_chat_gift_log_fake]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-07-17 16:12:29
 *
 */
@Producer(entityType=UserChatGiftLogFakeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserChatGiftLogFakeMapper extends BaseMapper<UserChatGiftLogFakeEntity> {
    
}
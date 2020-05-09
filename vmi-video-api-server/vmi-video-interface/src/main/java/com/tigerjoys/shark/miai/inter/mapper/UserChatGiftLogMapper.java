package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  礼物消费记录[t_user_chat_gift_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-07-24 12:22:00
 *
 */
@Producer(entityType=UserChatGiftLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserChatGiftLogMapper extends BaseMapper<UserChatGiftLogEntity> {
    
}
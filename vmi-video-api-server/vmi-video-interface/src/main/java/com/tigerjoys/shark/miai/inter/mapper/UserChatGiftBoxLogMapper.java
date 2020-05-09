package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftBoxLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户礼物盒流水[t_user_chat_gift_box_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-10-30 11:55:38
 *
 */
@Producer(entityType=UserChatGiftBoxLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserChatGiftBoxLogMapper extends BaseMapper<UserChatGiftBoxLogEntity> {
    
}
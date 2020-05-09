package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.MessageUserReadEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户阅读消息条数记录[t_message_user_read]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-09 17:25:45
 *
 */
@Producer(entityType=MessageUserReadEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface MessageUserReadMapper extends BaseMapper<MessageUserReadEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.MessagePushTemplateEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  系统消息[t_message_push_template]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-10-14 15:44:34
 *
 */
@Producer(entityType=MessagePushTemplateEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface MessagePushTemplateMapper extends BaseMapper<MessagePushTemplateEntity> {
    
}
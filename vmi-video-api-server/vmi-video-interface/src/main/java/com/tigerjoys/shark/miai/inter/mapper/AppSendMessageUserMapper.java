package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppSendMessageUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  客服消息历史表[t_app_send_message_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-06 16:29:41
 *
 */
@Producer(entityType=AppSendMessageUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppSendMessageUserMapper extends BaseMapper<AppSendMessageUserEntity> {
    
}
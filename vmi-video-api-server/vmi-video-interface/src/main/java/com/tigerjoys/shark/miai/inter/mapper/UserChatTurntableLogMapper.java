package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatTurntableLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  转盘消费记录[t_user_chat_turntable_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-27 16:13:40
 *
 */
@Producer(entityType=UserChatTurntableLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserChatTurntableLogMapper extends BaseMapper<UserChatTurntableLogEntity> {
    
}
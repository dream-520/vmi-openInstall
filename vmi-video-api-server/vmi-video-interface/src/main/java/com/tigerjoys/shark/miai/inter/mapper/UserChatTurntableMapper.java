package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatTurntableEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  转盘项目列表[t_user_chat_turntable]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-26 18:11:17
 *
 */
@Producer(entityType=UserChatTurntableEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserChatTurntableMapper extends BaseMapper<UserChatTurntableEntity> {
    
}
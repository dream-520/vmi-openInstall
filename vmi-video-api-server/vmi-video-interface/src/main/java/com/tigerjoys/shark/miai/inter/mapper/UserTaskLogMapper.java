package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTaskLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户参加活动记录表[t_user_task_log]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-09-05 14:15:54
 *
 */
@Producer(entityType=UserTaskLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserTaskLogMapper extends BaseMapper<UserTaskLogEntity> {
    
}
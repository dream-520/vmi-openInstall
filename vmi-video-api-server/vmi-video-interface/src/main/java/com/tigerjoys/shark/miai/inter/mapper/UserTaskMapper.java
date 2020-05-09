package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTaskEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  活动任务列表[t_user_task]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-09-05 14:05:20
 *
 */
@Producer(entityType=UserTaskEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserTaskMapper extends BaseMapper<UserTaskEntity> {
    
}
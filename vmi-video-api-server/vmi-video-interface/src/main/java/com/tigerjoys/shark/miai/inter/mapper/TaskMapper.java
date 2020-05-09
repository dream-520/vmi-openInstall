package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.TaskEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  任务信息表[t_task]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-27 15:03:44
 *
 */
@Producer(entityType=TaskEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface TaskMapper extends BaseMapper<TaskEntity> {
    
}
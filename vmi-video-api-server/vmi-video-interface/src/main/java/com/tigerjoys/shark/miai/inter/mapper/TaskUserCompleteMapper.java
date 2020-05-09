package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.TaskUserCompleteEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户任务完成记录[t_task_user_complete]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-27 15:03:44
 *
 */
@Producer(entityType=TaskUserCompleteEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface TaskUserCompleteMapper extends BaseMapper<TaskUserCompleteEntity> {
  
	/**
	 * 某时间以后任务完成数量
	 * @param userId
	 * @param taskId
	 * @param date
	 * @return
	 */
	@Select("select count(*) from t_task_user_complete where user_id=#{userId} and task_id=#{taskId} and create_time>#{date} for update")
	public Long timesLocked(@Param("userId") long userId, @Param("taskId") long taskId, @Param("date") String date);
}
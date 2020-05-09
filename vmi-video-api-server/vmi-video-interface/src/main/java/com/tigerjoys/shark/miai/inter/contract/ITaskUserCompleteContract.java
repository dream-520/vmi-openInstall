package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.TaskUserCompleteEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户任务完成记录[t_task_user_complete]表 接口类
 * @author mouzhanpeng
 * @Date 2017-11-27 15:03:44
 *
 */
public interface ITaskUserCompleteContract extends BaseContract<TaskUserCompleteEntity> {
	
	/**
	 * 某时间后任务完成数量
	 * @param userId
	 * @param taskId
	 * @return
	 */
	public long timesLocked(long userId, long taskId, String date);
}

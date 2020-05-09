package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ITaskUserCompleteContract;
import com.tigerjoys.shark.miai.inter.entity.TaskUserCompleteEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.TaskUserCompleteMapper;

/**
 * 数据库中  用户任务完成记录[t_task_user_complete]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-27 15:03:44
 *
 */
@Repository
public class TaskUserCompleteContractImpl extends AbstractBaseContract<TaskUserCompleteEntity , TaskUserCompleteMapper> implements ITaskUserCompleteContract {

	@Override
	public long timesLocked(long userId, long taskId, String date) {
		return Tools.parseLong(mapper.timesLocked(userId, taskId, date));
	}
}

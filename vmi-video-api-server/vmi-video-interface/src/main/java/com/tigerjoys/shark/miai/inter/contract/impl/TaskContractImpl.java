package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ITaskContract;
import com.tigerjoys.shark.miai.inter.entity.TaskEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.TaskMapper;

/**
 * 数据库中  任务信息表[t_task]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-27 15:03:44
 *
 */
@Repository
public class TaskContractImpl extends AbstractBaseContract<TaskEntity , TaskMapper> implements ITaskContract {
	
}

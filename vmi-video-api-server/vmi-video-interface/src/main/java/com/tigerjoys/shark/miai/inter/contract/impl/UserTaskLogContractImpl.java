package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserTaskLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserTaskLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserTaskLogMapper;

/**
 * 数据库中  用户参加活动记录表[t_user_task_log]表 接口实现类
 * @author lipeng
 * @Date 2019-09-05 14:15:54
 *
 */
@Repository
public class UserTaskLogContractImpl extends AbstractBaseContract<UserTaskLogEntity , UserTaskLogMapper> implements IUserTaskLogContract {
	
}

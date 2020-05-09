package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTaskContract;
import com.tigerjoys.shark.miai.inter.entity.UserTaskEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserTaskMapper;

/**
 * 数据库中  活动任务列表[t_user_task]表 接口实现类
 * @author lipeng
 * @Date 2019-09-05 14:05:20
 *
 */
@Repository
public class UserTaskContractImpl extends RedisCacheContract<UserTaskEntity , UserTaskMapper> implements IUserTaskContract {
	
}

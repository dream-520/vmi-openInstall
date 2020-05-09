package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContentContract;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicContentEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserDynamicContentMapper;

/**
 * 数据库中  动态内容表[t_user_dynamic_content]表 接口实现类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Repository
public class UserDynamicContentContractImpl extends RedisCacheContract<UserDynamicContentEntity , UserDynamicContentMapper> implements IUserDynamicContentContract {
	
}

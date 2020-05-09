package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserFriendsAddLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserFriendsAddLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserFriendsAddLogMapper;

/**
 * 数据库中  用于记录是否添加过好友关系表[t_user_friends_add_log]表 接口实现类
 * @author shiming
 * @Date 2017-09-13 10:30:21
 *
 */
@Repository
public class UserFriendsAddLogContractImpl extends AbstractBaseContract<UserFriendsAddLogEntity , UserFriendsAddLogMapper> implements IUserFriendsAddLogContract {
	
}

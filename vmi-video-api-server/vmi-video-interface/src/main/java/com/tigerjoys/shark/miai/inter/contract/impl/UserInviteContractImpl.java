package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserInviteMapper;

/**
 * 数据库中  用户邀请关系表[t_user_invite]表 接口实现类
 * @author yangjunming
 * @Date 2017-10-21 17:05:26
 *
 */
@Repository
public class UserInviteContractImpl extends AbstractBaseContract<UserInviteEntity , UserInviteMapper> implements IUserInviteContract {
	
}

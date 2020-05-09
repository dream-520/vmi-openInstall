package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserInviteCpsIncomeLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserInviteCpsIncomeLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserInviteCpsIncomeLogMapper;

/**
 * 数据库中  [t_user_invite_cps_income_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-17 11:23:55
 *
 */
@Repository
public class UserInviteCpsIncomeLogContractImpl extends AbstractBaseContract<UserInviteCpsIncomeLogEntity , UserInviteCpsIncomeLogMapper> implements IUserInviteCpsIncomeLogContract {
	
}

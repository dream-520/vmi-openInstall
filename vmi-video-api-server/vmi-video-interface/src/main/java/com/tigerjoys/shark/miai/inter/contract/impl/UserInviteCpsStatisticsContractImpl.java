package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserInviteCpsStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.UserInviteCpsStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserInviteCpsStatisticsMapper;

/**
 * 数据库中  [t_user_invite_cps_statistics]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-17 11:23:55
 *
 */
@Repository
public class UserInviteCpsStatisticsContractImpl extends AbstractBaseContract<UserInviteCpsStatisticsEntity , UserInviteCpsStatisticsMapper> implements IUserInviteCpsStatisticsContract {
	
}

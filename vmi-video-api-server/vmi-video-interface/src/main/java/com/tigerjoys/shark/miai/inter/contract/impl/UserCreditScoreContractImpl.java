package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserCreditScoreContract;
import com.tigerjoys.shark.miai.inter.entity.UserCreditScoreEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserCreditScoreMapper;

/**
 * 数据库中  用户信用分账户[t_user_credit_score]表 接口实现类
 * @author liuman
 * @Date 2017-08-14 18:43:02
 *
 */
@Repository
public class UserCreditScoreContractImpl extends AbstractBaseContract<UserCreditScoreEntity , UserCreditScoreMapper> implements IUserCreditScoreContract {
	
}

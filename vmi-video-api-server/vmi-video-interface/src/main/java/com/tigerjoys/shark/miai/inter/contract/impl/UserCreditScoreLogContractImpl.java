package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserCreditScoreLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserCreditScoreLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserCreditScoreLogMapper;

/**
 * 数据库中  用户信用分账户流水[t_user_credit_score_log]表 接口实现类
 * @author liuman
 * @Date 2017-08-18 19:52:17
 *
 */
@Repository
public class UserCreditScoreLogContractImpl extends AbstractBaseContract<UserCreditScoreLogEntity , UserCreditScoreLogMapper> implements IUserCreditScoreLogContract {
	
}

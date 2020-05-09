package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserIncomeWithdrawalContract;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeWithdrawalEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserIncomeWithdrawalMapper;

/**
 * 数据库中  提现申请管理表[t_user_income_withdrawal]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
@Repository
public class UserIncomeWithdrawalContractImpl extends AbstractBaseContract<UserIncomeWithdrawalEntity , UserIncomeWithdrawalMapper> implements IUserIncomeWithdrawalContract {

}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IIosUserLoginSmsContract;
import com.tigerjoys.shark.miai.inter.entity.IosUserLoginSmsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.IosUserLoginSmsMapper;

/**
 * 数据库中  IOS提审账号固定验证码[t_ios_user_login_sms]表 接口实现类
 * @author yangjunming
 * @Date 2020-02-21 17:51:12
 *
 */
@Repository
public class IosUserLoginSmsContractImpl extends AbstractBaseContract<IosUserLoginSmsEntity , IosUserLoginSmsMapper> implements IIosUserLoginSmsContract {
	
}

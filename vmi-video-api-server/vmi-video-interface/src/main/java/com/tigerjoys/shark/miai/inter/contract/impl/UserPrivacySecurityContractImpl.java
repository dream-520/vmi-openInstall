package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserPrivacySecurityContract;
import com.tigerjoys.shark.miai.inter.entity.UserPrivacySecurityEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserPrivacySecurityMapper;

/**
 * 数据库中  用户隐私安全表[t_user_privacy_security]表 接口实现类
 * @author lipeng
 * @Date 2017-12-25 10:26:14
 *
 */
@Repository
public class UserPrivacySecurityContractImpl extends AbstractBaseContract<UserPrivacySecurityEntity , UserPrivacySecurityMapper> implements IUserPrivacySecurityContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserExtensionContract;
import com.tigerjoys.shark.miai.inter.entity.UserExtensionEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserExtensionMapper;

/**
 * 数据库中  用户扩展信息表[t_user_extension]表 接口实现类
 * @author chengang
 * @Date 2017-06-21 10:51:05
 *
 */
@Repository
public class UserExtensionContractImpl extends AbstractBaseContract<UserExtensionEntity , UserExtensionMapper> implements IUserExtensionContract {
	
}

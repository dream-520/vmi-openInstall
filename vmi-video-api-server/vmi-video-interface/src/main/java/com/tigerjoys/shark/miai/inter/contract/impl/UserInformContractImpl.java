package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserInformContract;
import com.tigerjoys.shark.miai.inter.entity.UserInformEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserInformMapper;

/**
 * 数据库中  用户举报表[t_user_inform]表 接口实现类
 * @author lipeng
 * @Date 2017-08-17 11:03:29
 *
 */
@Repository
public class UserInformContractImpl extends AbstractBaseContract<UserInformEntity , UserInformMapper> implements IUserInformContract {
	
}

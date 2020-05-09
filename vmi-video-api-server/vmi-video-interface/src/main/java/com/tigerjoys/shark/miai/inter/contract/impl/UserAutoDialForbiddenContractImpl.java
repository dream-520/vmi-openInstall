package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserAutoDialForbiddenContract;
import com.tigerjoys.shark.miai.inter.entity.UserAutoDialForbiddenEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserAutoDialForbiddenMapper;

/**
 * 数据库中  用户自动拨打禁用Id[t_user_auto_dial_forbidden]表 接口实现类
 * @author yangjunming
 * @Date 2020-02-26 15:14:00
 *
 */
@Repository
public class UserAutoDialForbiddenContractImpl extends AbstractBaseContract<UserAutoDialForbiddenEntity , UserAutoDialForbiddenMapper> implements IUserAutoDialForbiddenContract {
	
}

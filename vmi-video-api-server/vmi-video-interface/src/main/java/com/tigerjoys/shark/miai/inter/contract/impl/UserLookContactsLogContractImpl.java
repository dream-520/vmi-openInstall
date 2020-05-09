package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserLookContactsLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserLookContactsLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserLookContactsLogMapper;

/**
 * 数据库中  用户查看联系方式记录表[t_user_look_contacts_log]表 接口实现类
 * @author lipeng
 * @Date 2017-11-14 16:58:32
 *
 */
@Repository
public class UserLookContactsLogContractImpl extends AbstractBaseContract<UserLookContactsLogEntity , UserLookContactsLogMapper> implements IUserLookContactsLogContract {
	
}

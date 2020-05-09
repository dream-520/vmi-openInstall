package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserToOtherContract;
import com.tigerjoys.shark.miai.inter.entity.UserToOtherEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserToOtherMapper;

/**
 * 数据库中  聊天历史用户之间映射关系[t_user_to_other]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-04 17:03:38
 *
 */
@Repository
public class UserToOtherContractImpl extends AbstractBaseContract<UserToOtherEntity , UserToOtherMapper> implements IUserToOtherContract {
	
}

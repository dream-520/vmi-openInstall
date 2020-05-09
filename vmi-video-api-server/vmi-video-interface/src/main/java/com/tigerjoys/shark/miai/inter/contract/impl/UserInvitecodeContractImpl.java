package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserInvitecodeContract;
import com.tigerjoys.shark.miai.inter.entity.UserInvitecodeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserInvitecodeMapper;

/**
 * 数据库中  [t_user_invitecode]表 接口实现类
 * @author yangjunming
 * @Date 2017-10-21 17:05:26
 *
 */
@Repository
public class UserInvitecodeContractImpl extends AbstractBaseContract<UserInvitecodeEntity , UserInvitecodeMapper> implements IUserInvitecodeContract {

	@Override
	public UserInvitecodeEntity getInviteCode() throws Exception {
		return mapper.lockByStatus();
	}

	@Override
	public int updateUsed(long id) throws Exception {
		
		return mapper.updateByStatement("status=1", "id="+id);
	}
	
	
}

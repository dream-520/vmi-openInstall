package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserInvitecodeEntity;

/**
 * 数据库中  [t_user_invitecode]表 接口类
 * @author yangjunming
 * @Date 2017-10-21 17:05:26
 *
 */
public interface IUserInvitecodeContract extends BaseContract<UserInvitecodeEntity> {
	/**
	 * 获取验证码记录
	 * @return
	 * @throws Exception
	 */
	public UserInvitecodeEntity getInviteCode() throws Exception;
	
	/**
	 * 更新成已使用
	 * @id	
	 * @return
	 * @throws Exception
	 */
	public int updateUsed(long id) throws Exception;
	
	
}

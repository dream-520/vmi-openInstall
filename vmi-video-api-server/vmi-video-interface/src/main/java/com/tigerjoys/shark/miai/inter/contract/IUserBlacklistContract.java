package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserBlacklistEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户黑名单表[t_user_blacklist]表 接口类
 * @author lipeng
 * @Date 2017-08-17 11:01:38
 *
 */
public interface IUserBlacklistContract extends BaseContract<UserBlacklistEntity> {
	
	/**
	 * 获取黑名单
	 * @param userid - 用户ID
	 * @param blackid - 黑名单ID
	 * @return UserBlacklistEntity
	 * @throws Exception
	 */
	public UserBlacklistEntity getUserBlackList(long userid , long blackid) throws Exception;
	
}

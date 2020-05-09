package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserMemoEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户备注表[t_user_memo]表 接口类
 * @author chengang
 * @Date 2017-05-08 11:43:45
 *
 */
public interface IUserMemoContract extends BaseContract<UserMemoEntity> {
	
	/**
	 * 根据用户ID和类型获取枚举信息
	 * @param userid - 用户ID
	 * @param type - 枚举类型
	 * @return UserMemoEntity
	 * @throws Exception
	 */
	public UserMemoEntity getUserMemo(long userid , int type) throws Exception;
	
}

package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.VUserOnlineEntity;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_v_user_online]表 接口类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
public interface IVUserOnlineContract extends BaseContract<VUserOnlineEntity> {
	
	public List<Long> loadUserIds(String today, String yesterday);
	
	public List<VUserOnlineEntity> loadUsers(String today, String yesterday);
}

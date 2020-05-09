package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.VUserOnlineStatEntity;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_v_user_online_stat]表 接口类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
public interface IVUserOnlineStatContract extends BaseContract<VUserOnlineStatEntity> {
	
	public List<Long> loadUserIds(String yesterday);
}

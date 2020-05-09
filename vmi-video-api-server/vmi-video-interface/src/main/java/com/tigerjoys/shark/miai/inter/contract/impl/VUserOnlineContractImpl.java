package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVUserOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.VUserOnlineEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VUserOnlineMapper;

/**
 * 数据库中  [t_v_user_online]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Repository
public class VUserOnlineContractImpl extends AbstractBaseContract<VUserOnlineEntity , VUserOnlineMapper> implements IVUserOnlineContract {
	
	@Override
	public List<Long> loadUserIds(String today, String yesterday) {
		return mapper.loadUserIds(today, yesterday);
	}

	@Override
	public List<VUserOnlineEntity> loadUsers(String today, String yesterday) {
		return mapper.loadUsers(today, yesterday);
	}
}

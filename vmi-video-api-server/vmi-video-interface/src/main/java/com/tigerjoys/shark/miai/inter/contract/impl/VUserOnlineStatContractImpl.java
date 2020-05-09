package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVUserOnlineStatContract;
import com.tigerjoys.shark.miai.inter.entity.VUserOnlineStatEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VUserOnlineStatMapper;

/**
 * 数据库中  [t_v_user_online_stat]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Repository
public class VUserOnlineStatContractImpl extends AbstractBaseContract<VUserOnlineStatEntity , VUserOnlineStatMapper> implements IVUserOnlineStatContract {
	
	@Override
	public List<Long> loadUserIds(String yesterday) {
		return mapper.loadUserIds(yesterday);
	}
	
}

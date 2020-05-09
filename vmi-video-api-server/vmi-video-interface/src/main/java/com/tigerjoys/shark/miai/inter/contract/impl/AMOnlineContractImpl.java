package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAMOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AMOnlineEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AMOnlineMapper;

/**
 * 数据库中  [t_a_m_online]表 接口实现类
 * @author shiming
 * @Date 2018-11-14 16:14:24
 *
 */
@Repository
public class AMOnlineContractImpl extends AbstractBaseContract<AMOnlineEntity , AMOnlineMapper> implements IAMOnlineContract {

	@Override
	public int onlineNum(String start, String end, int type) {
		return mapper.onlineNum(start, end, type);
	}

	@Override
	public int talkingNum(String start, String end, int type) {
		return mapper.talkingNum(start, end, type);
	}

	@Override
	public List<Long> loadUserIds(String start, String end, int type) {
		return mapper.loadUserIds(start, end, type);
	}
	
}

package com.tigerjoys.shark.miai.inter.contract;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.AMOnlineEntity;

/**
 * 数据库中  [t_a_m_online]表 接口类
 * @author shiming
 * @Date 2018-11-14 16:14:24
 *
 */
public interface IAMOnlineContract extends BaseContract<AMOnlineEntity> {
	
	public int onlineNum(String start, String end, int type);
	
	public int talkingNum(String start, String end, int type);
	
	public List<Long> loadUserIds(String start, String end, int type);
	
}

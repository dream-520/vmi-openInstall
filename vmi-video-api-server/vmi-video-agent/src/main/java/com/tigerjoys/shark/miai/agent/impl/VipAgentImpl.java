package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IVipAgent;
import com.tigerjoys.shark.miai.inter.contract.IVipContract;
import com.tigerjoys.shark.miai.inter.contract.IVipLogContract;
import com.tigerjoys.shark.miai.inter.entity.VipEntity;
import com.tigerjoys.shark.miai.inter.entity.VipLogEntity;

/**
 * 代金券代理接口实现类
 * 
 * @author yangjunming
 *
 */
@Service
public class VipAgentImpl implements IVipAgent {

	private final long one_day = 3600L * 1000 * 24;

	private final int months_day = 30;

	@Autowired
	private IVipContract vipContract;

	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private IVipLogContract vipLogContract;

	@Override
	public int giveAway(long userid, int month, String orderId) throws Exception {
		return giveAwayDay(userid, month * months_day, orderId);
	}

	@Override
	public int giveAwayDay(long userid, int day, String orderId) throws Exception {
		Date current = new Date();
		VipEntity vip = vipContract.findById(userid);
		if (Tools.isNull(vip)) {
			vip = new VipEntity();
			vip.setUserid(userid);
			vip.setCreate_time(current);
			vip.setUpdate_time(current);
			long endTime = current.getTime() + day * one_day;
			vip.setExpire_time(new Date(endTime));
			vipContract.insert(vip);
		} else {
			vipContract.updateVipTime(userid, day);
		}
		vip = vipContract.findById(userid);
		userAgent.updateVip(userid, 1, vip.getExpire_time());

		VipLogEntity vipLog = new VipLogEntity();
		vipLog.setUserid(userid);
		vipLog.setCategory_id(0L);
		vipLog.setDiamond(0L);
		vipLog.setMonths(day);
		vipLog.setCreate_time(current);
		vipLog.setTransaction_flow(orderId);
		vipLogContract.insert(vipLog);
		return 1;
	}

}

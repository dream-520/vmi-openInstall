package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserVipAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;

@Service
public class UserVipAgentImpl implements IUserVipAgent{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserAgent userAgent;
	
	@Override
	public void increaseVipDay(long userid, int days) {
		try {
			logger.info("给对应的用户处理vip userid"+userid +" day:"+days);
			UserBO bo = userAgent.findById(userid);
			if(Tools.isNotNull(bo)) {
				//处理对应的vip时间
				Date currDate = new Date();
				//Date currDate = Tools.getDate(Tools.getDate());
				Date expireDate = null;
				if(Tools.isNotNull(bo.getVipExpire())) {
					//已经买过vip了
					if (bo.getVipExpire().getTime() > currDate.getTime()) {
						expireDate = new Date(bo.getVipExpire().getTime() + Tools.DAY_MILLIS * days);
					} else {
						expireDate = new Date(currDate.getTime() + Tools.DAY_MILLIS * days);
					}
				} else {
					//尚未买过vip
					expireDate = new Date(currDate.getTime() + Tools.DAY_MILLIS * days);
				}
				userAgent.updateVip(userid, 1, expireDate);
			}
		} catch (Exception e) {
			logger.error("处理用户vip操作处理问题 userid"+userid);
		}
	}

}

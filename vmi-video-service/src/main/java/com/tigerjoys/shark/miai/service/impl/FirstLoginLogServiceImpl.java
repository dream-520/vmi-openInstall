package com.tigerjoys.shark.miai.service.impl;

import org.shark.miai.common.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tigerjoys.shark.miai.inter.contract.IFirstLoginLogContract;
import com.tigerjoys.shark.miai.inter.entity.FirstLoginLogEntity;
import com.tigerjoys.shark.miai.service.IFirstLoginLogService;

@Service
public class FirstLoginLogServiceImpl implements IFirstLoginLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(FirstLoginLogServiceImpl.class);
	
	@Autowired
	private IFirstLoginLogContract firstLoginLogContract;//用户首次登录服务
	
	/**
	 * 
	 * @param clientId 用户请求的id
	 * @return true : 已经首次登录过, false : 今天还没有登录过
	 * @throws Exception 
	 */
	@Override
	public boolean judgeIsFirstLogin(Long userId) throws Exception {
		boolean loginFlag = false;
		//判断是否为首次登录 newClientId + yyyyMMdd存成唯一索引查询登录日志表
		String checkIsFirstLogin = this.getCheckIsFirstLogin(userId);
		logger.info("判断是否首次登录用户的check为:{}", checkIsFirstLogin);
		//首次登录后,完成了首次登陆任务,小红点要亮起
		FirstLoginLogEntity firstLogin = firstLoginLogContract.findByProperty("userid_perday", checkIsFirstLogin);
		if (firstLogin != null) {
			String userIdPerDay = firstLogin.getUserid_perday();
			if (!StringUtils.isEmpty(userIdPerDay)) {
				if (userIdPerDay.length() > 0) {//假如今天已经登录了
					loginFlag = true;
				}
			}
		}
		
		return loginFlag;
	}

	@Override
	public void recordFirstLogin(Long userId) throws Exception {
		FirstLoginLogEntity firstLogin = new FirstLoginLogEntity();
		firstLogin.setUserid_perday(this.getCheckIsFirstLogin(userId));
		firstLoginLogContract.insert(firstLogin);
	}
	
	private String getCheckIsFirstLogin(long userId) {
		return String.valueOf(userId).concat(TimeTools.getCurrentDateTime(TimeTools.YYYYMMDD));
	}

}

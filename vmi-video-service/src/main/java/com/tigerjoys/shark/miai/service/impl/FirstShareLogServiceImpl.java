package com.tigerjoys.shark.miai.service.impl;

import org.shark.miai.common.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tigerjoys.shark.miai.inter.contract.IFirstShareLogContract;
import com.tigerjoys.shark.miai.inter.entity.FirstShareLogEntity;
import com.tigerjoys.shark.miai.service.IFirstShareLogService;

@Service
public class FirstShareLogServiceImpl implements IFirstShareLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(FirstShareLogServiceImpl.class);
	
	@Autowired
	private IFirstShareLogContract firstShareLogContract;
	
	/**
	 * 
	 * @param clientId 用户请求的id
	 * @return true : 已经首次分享过, false : 今天还没有分享过
	 * @throws Exception 
	 */
	@Override
	public boolean judgeIsFirstShare(Long userId) throws Exception {
		boolean loginFlag = false;
		//判断是否为首次登录 newClientId + yyyyMMdd存成唯一索引查询登录日志表
		String checkIsFirstShare = this.getCheckIsFirstShare(userId);
		logger.info("判断是否首次分享用户的check为:{}", checkIsFirstShare);
		//首次登录后,完成了首次登陆任务,小红点要亮起
		FirstShareLogEntity firstShare= firstShareLogContract.findByProperty("userid_perday", checkIsFirstShare);
		if (firstShare != null) {
			String userIdPerDay = firstShare.getUserid_perday();
			if (!StringUtils.isEmpty(userIdPerDay)) {
				if (userIdPerDay.length() > 0) {//假如今天已经分享了
					loginFlag = true;
				}
			}
		}
		
		return loginFlag;
	}

	@Override
	public void recordFirstShare(Long userId) throws Exception {
		FirstShareLogEntity firstLogin = new FirstShareLogEntity();
		firstLogin.setUserid_perday(this.getCheckIsFirstShare(userId));
		firstShareLogContract.insert(firstLogin);
	}
	
	private String getCheckIsFirstShare(long userId) {
		return String.valueOf(userId).concat(TimeTools.getCurrentDateTime(TimeTools.YYYYMMDD));
	}

}

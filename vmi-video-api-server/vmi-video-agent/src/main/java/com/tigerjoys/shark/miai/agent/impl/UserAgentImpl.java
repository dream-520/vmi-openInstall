package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserFullBO;
import com.tigerjoys.shark.miai.agent.dto.UserLoginBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserCreatDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserModifyDto;
import com.tigerjoys.shark.miai.agent.enums.BadgeTypeEnum;
import com.tigerjoys.shark.miai.agent.event.ICreateUserCallback;
import com.tigerjoys.shark.miai.agent.service.IUserAgentService;

/**
 * 用户代理服务实现类
 * @author chengang
 *
 */
@Service
public class UserAgentImpl implements IUserAgent {
	
	@Autowired
	private IUserAgentService userService;
	
	@Override
	public UserBO findById(long userid) throws Exception {
		return userService.findById(userid);
	}
	
	@Override
	public UserFullBO findFullById(long userid) throws Exception {
		return userService.findFullById(userid);
	}

	@Override
	public Map<Long, UserBO> findById(List<Long> userIds) throws Exception {
		return userService.findById(userIds);
	}

	@Override
	public UserBO findByIdRefreshCache(long userid) throws Exception {
		return userService.findByIdRefreshCache(userid);
	}

	@Override
	public UserBO findByUsername(String username) throws Exception {
		return userService.findByUsername(username);
	}

	@Override
	public UserBO findByNickname(String nickname) throws Exception {
		return userService.findByNickname(nickname);
	}
	
	@Override
	public UserBO findByIdcard(int idcard) throws Exception {
		return userService.findByIdcard(idcard);
	}

	@Override
	public UserBO findByUniqueKey(String uniqueKey) throws Exception {
		return userService.findByUniqueKey(uniqueKey);
	}
	
	@Override
	public UserBO findByClientid(String clientid) throws Exception {
		return userService.findByClientid(clientid);
	}
	
	@Override
	public UserFullBO findFullByUniqueKey(String uniqueKey) throws Exception {
		return userService.findFullByUniqueKey(uniqueKey);
	}

	@Override
	public UserBO findByMobile(String mobile) throws Exception {
		return userService.findByMobile(mobile);
	}

	@Override
	public UserFullBO createUser(UserCreatDto dto,ICreateUserCallback callback) throws Exception {
		return userService.createUser(dto, callback);
	}
	
	@Override
	public AgentResult updateUsername(long userid , String newUsername) throws Exception {
		return userService.updateUsername(userid, newUsername);
	}
	
	@Override
	public void updateUserLabels(long userid , String labels) throws Exception {
		userService.updateUserLabels(userid, labels);
	}

	@Override
	public void updateUser(UserModifyDto dto) throws Exception {
		userService.updateUser(dto);
	}

	@Override
	public void updateUserLevel(long userid , int levelId) throws Exception {
		userService.updateUserLevel(userid, levelId);
	}

	@Override
	public void updateVip(long userid , int vip , Date expireDate) throws Exception {
		userService.updateVip(userid, vip , expireDate);
	}

	@Override
	public void updateFlowerToasted(long userid) throws Exception {
		userService.updateFlowerToasted(userid);
	}

	@Override
	public void updateSvip(long userid) throws Exception {
		userService.updateSvip(userid);
	}
	
	@Override
	public Date updateTalentVip(long userid , int vip , int day) throws Exception {
		return userService.updateTalentVip(userid, vip, day);
	}
	
	@Override
	public void updateHonestyBadge(long userId, BadgeTypeEnum type, Date expireDate) throws Exception {
		userService.updateHonestyBadge(userId, type, expireDate);
	}
	
	@Override
	public void updateVideoAuth(long userid , String video_auth) throws Exception {
		userService.updateVideoAuth(userid, video_auth);
	}
	
	@Override
	public void updateWaiter(long userid , int levelId) throws Exception {
		userService.updateWaiter(userid, levelId);
	}

	@Override
	public void updateVideoChat(long userid, int allow) throws Exception {
		userService.updateVideoChat(userid, allow);
	}
	
	@Override
	public void updateUserDisturb(long userid, int disturb) throws Exception {
		userService.updateUserDisturb(userid, disturb);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void changeUserStatus(long userid , int status , String memo) throws Exception {
		userService.changeUserStatus(userid, status, memo);
	}	

	@Override
	public void updateUserHuaweiToken(long userid, String token) throws Exception {
		userService.updateUserHuaweiToken(userid, token);
	}
	
	@Override
	public void updateUserPushChannel(long userid, int pushChanel) throws Exception {
		userService.updateUserPushChannel(userid, pushChanel);
	}
	
	@Override
	public void loginOut(long userid, UserLoginBO loginbo) throws Exception {
		userService.loginOut(userid, loginbo);
	}

	@Override
	public void changeUserProxy(long userid, Integer inlet, int status, String memo) throws Exception {
		userService.changeUserProxy(userid,inlet, status, memo);
	}
	
	@Override
	public void subscribePushTopicByAlias(Integer oldPlatform , String oldTopic , int newPlatform , String newTopic , List<String> clientList) {
		userService.subscribePushTopicByAlias(oldPlatform, oldTopic, newPlatform, newTopic, clientList);
	}

	@Override
	public boolean ifVideoExist(long userid) throws Exception {
		UserBO bo = findById(userid);
		if (bo.getVideoAuth()!=null) {
			return true;
		}
		return false;
	}

	@Override
	public void saveUserCanCallLog(long userId) throws Exception {
		userService.saveUserCanCallLog(userId);
	}

	@Override
	public void clearUserCache(long userid) {
		userService.clearUserCache(userid);
	}

	
	
}

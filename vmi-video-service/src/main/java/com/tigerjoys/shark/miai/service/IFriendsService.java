package com.tigerjoys.shark.miai.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tigerjoys.shark.miai.dto.service.FriendListResultDto;

/**
 * 好友服务接口
 * @author chengang
 *
 */
@Service
public interface IFriendsService {
	
	/**
	 * 关注
	 * @throws Exception
	 */
	public Map<String, Object> attentionUser(long userid,long friendid,int state) throws Exception;
	
	/**
	 * 我和好友的关系状态
	 * @return ActionResult
	 * @throws Exception
	 */
	public int relationStatus(long userid,long friendid) throws Exception;
	
	/**
	 * 好友列表
	 * @return ActionResult
	 * @throws Exception
	 */
	public List<FriendListResultDto> getFriendList(long userid,long lastUserFrId,int pagesize,int type) throws Exception;
	
}

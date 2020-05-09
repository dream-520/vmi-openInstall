package com.tigerjoys.shark.miai.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.dto.service.FriendListResultDto;
import com.tigerjoys.shark.miai.service.IFriendsService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 好友关系相关功能的测试
 * @author shiming
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class FriendServiceTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IFriendsService friendsService;

	/**
	 * 测试关注与取消关注
	 */
	@Test
	public void testAttentionUser() {
		long userid = 5031969847050496L;
		long friendid = 8158578022088960L;
		int state = 0;
		try {
//			ActionResult result = friendsService.attentionUser(userid,friendid,state);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试获取好友列表数据
	 */
	@Test
	public void testGetFriendList() {
		long userid = 23551151479783680L;
		long lastUserFrId = 0;
		int pageSize = 5;
		int type = 2;
		try {
			List<FriendListResultDto> resultList = friendsService.getFriendList(userid,lastUserFrId,pageSize,type);
			if(Tools.isNotNull(resultList) && resultList.size() > 0) {
				int total = resultList.size();
				if(total > pageSize) {
					resultList = resultList.subList(0, pageSize);
				}
				lastUserFrId = resultList.get(resultList.size()-1).getId();
				List<FriendListResultDto> resultList2 = friendsService.getFriendList(userid,lastUserFrId,pageSize,type);
				total = resultList2.size();
				if(total > pageSize) {
					resultList2 = resultList2.subList(0, pageSize);
				}
				lastUserFrId = resultList2.get(resultList2.size()-1).getId();
				List<FriendListResultDto> resultList3 = friendsService.getFriendList(userid,lastUserFrId,pageSize,type);
				total = resultList3.size();
				if(total > pageSize) {
					resultList3 = resultList3.subList(0, pageSize);
				}
				lastUserFrId = resultList3.get(resultList3.size()-1).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

package com.tigerjoys.shark.miai.agent.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IUserFriendAgent;
import com.tigerjoys.shark.miai.agent.dto.UserFriendBO;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 用户好友列表测试类
 * @author chengang
 *
 */
public class UserFriendAgentTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserFriendAgent userFriendAgent;
	
	private final long userid = 10001;//测试用户
	private final long friendid = 10002;//测试好友
	
	/**
	 * 获取用户的指定好友关系
	 * @throws Exception
	 */
	@Test
	public void findUserFriend() throws Exception {
		UserFriendBO bo = userFriendAgent.findUserFriendWithoutCache(10022566 , 10022651);
		logger.info("{}" , JsonHelper.toJson(bo));

		bo = userFriendAgent.findUserFriend(10022566 , 10022651);
		logger.info("{}" , JsonHelper.toJson(bo));
		
		bo = userFriendAgent.findUserFriend(10022651 , 10022566);
		logger.info("{}" , JsonHelper.toJson(bo));
	}
	
	/**
	 * 测试分页获得用户的关注列表
	 * @throws Exception
	 */
	@Test
	public void findUserFriendListLimitTest() throws Exception {
		//先清空缓存
		userFriendAgent.clearUserFriendCache(userid);
		
		List<UserFriendBO> list = userFriendAgent.findUserFriendList(userid , 0 , 10);
		logger.info("{}" , JsonHelper.toJson(list));
		logger.info("list size {}" , Tools.length(list));
		
		if(Tools.isNotNull(list)) {
			list = userFriendAgent.findUserFriendList(userid , 321 , 10);
			logger.info("{}" , JsonHelper.toJson(list));
			logger.info("list size {}" , Tools.length(list));
		} else {
			logger.info("没有找到用户的好友数据!!!!");
		}
	}
	
	/**
	 * 测试获得用户关注的总数
	 * @throws Exception
	 */
	@Test
	public void findUserFriendCountTest() throws Exception {
		//先清空缓存
		userFriendAgent.clearUserFriendCache(userid);
		
		long c = userFriendAgent.findUserFriendCount(userid);
		logger.info("总关注数：{}" , c);
		
		c = userFriendAgent.findUserFriendCount(userid);
		logger.info("不走数据库 - 总关注数：{}" , c);
	}
	
	/**
	 * 测试分页获得用户的好友列表
	 * @throws Exception
	 */
	@Test
	public void findUserFriendPowderListLimitTest() throws Exception {
		//先清空缓存
		userFriendAgent.clearUserFriendCache(userid);
		
		List<UserFriendBO> list = userFriendAgent.findUserFriendPowderList(userid , 0 , 10);
		logger.info("{}" , JsonHelper.toJson(list));
		logger.info("list size {}" , Tools.length(list));
		
		if(Tools.isNotNull(list)) {
			list = userFriendAgent.findUserFriendPowderList(userid , 321 , 10);
			logger.info("{}" , JsonHelper.toJson(list));
			logger.info("list size {}" , Tools.length(list));
		} else {
			logger.info("没有找到用户的好友数据!!!!");
		}
	}
	
	/**
	 * 测试获得用户好友的总数
	 * @throws Exception
	 */
	@Test
	public void findUserFriendPowderCountTest() throws Exception {
		long c = userFriendAgent.findUserFriendPowderCount(userid);
		logger.info("总好友数：{}" , c);
		
		c = userFriendAgent.findUserFriendPowderCount(userid);
		logger.info("不走数据库 - 总好友数：{}" , c);
	}
	
	/**
	 * 测试分页获得用户的关注列表
	 * @throws Exception
	 */
	@Test
	public void findFriendUserListLimitTest() throws Exception {
		List<UserFriendBO> list = userFriendAgent.findFriendUserList(userid , 0 , 10);
		logger.info("{}" , JsonHelper.toJson(list));
		logger.info("list size {}" , Tools.length(list));
		
		if(Tools.isNotNull(list)) {
			list = userFriendAgent.findFriendUserList(userid , 321 , 10);
			logger.info("{}" , JsonHelper.toJson(list));
			logger.info("list size {}" , Tools.length(list));
		} else {
			logger.info("没有找到用户的好友数据!!!!");
		}
	}
	
	/**
	 * 测试获得用户关注的总数
	 * @throws Exception
	 */
	@Test
	public void findFriendUserCountTest() throws Exception {
		long c = userFriendAgent.findFriendUserCount(userid);
		logger.info("总好友数：{}" , c);
		
		c = userFriendAgent.findFriendUserCount(userid);
		logger.info("不走数据库 - 总好友数：{}" , c);
	}
	
	/**
	 * 测试批量查询好友
	 * @throws Exception
	 */
	@Test
	public void findUserFriendByFriendIdList() throws Exception {
		//清除缓存
		userFriendAgent.clearUserFriendCache(userid);
		
		//首先将10002加载到内存中
		UserFriendBO uf = userFriendAgent.findUserFriend(userid, friendid);
		logger.info("{}" , JsonHelper.toJson(uf));
		
		//这里再批量查询多个用户
		List<Long> friendidList = new ArrayList<>();
		friendidList.add(friendid);
		friendidList.add(10014212L);
		friendidList.add(10014852L);
		
		Map<Long, UserFriendBO>  map = userFriendAgent.findUserFriendByFriendIdList(userid, friendidList);
		map.forEach((k,v) -> System.out.println(k+"--"+JsonHelper.toJson(v)));
		
		//这里再查询一下10014852L是否在内存中
		uf = userFriendAgent.findUserFriend(userid, friendid);
		logger.info("{}" , JsonHelper.toJson(uf));
	}
	
	/**
	 * 测试添加好友
	 * @throws Exception
	 */
	@Test
	public void addFriendTest() throws Exception {
		long friendid = 10014842;
		
		//先清空缓存
		userFriendAgent.clearUserFriendCache(userid);
		userFriendAgent.clearUserFriendCache(friendid);
		
		boolean b = userFriendAgent.removeFriend(userid, friendid);
		logger.info("删除好友：" + b);
		
		//10014842
		b = userFriendAgent.addFriend(userid, friendid);
		Assert.assertTrue(b);
		logger.info("添加好友：" + b);
		
		//这里再让用户互相加好友
		b = userFriendAgent.addFriend(friendid, userid);
		Assert.assertTrue(b);
	}
	
	/**
	 * 测试机器人加好友
	 * @throws Exception
	 */
	@Test
	public void addRobotFriendTest() throws Exception {
		long userid = 1468715696849152L;
		
		//先清空缓存
		//userFriendAgent.clearUserFriendCache(userid);
		
		logger.info("粉丝数：{}" , userFriendAgent.findUserFriendCount(userid));
		logger.info("粉丝数：{}" , JsonHelper.toJson(userFriendAgent.findUserFriendList(userid , 0 , 100)));
		logger.info("好友数：{}" , userFriendAgent.findUserFriendPowderCount(userid));
	}

}

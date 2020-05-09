package com.tigerjoys.shark.miai.agent.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.shark.miai.common.util.DBHelper;
import org.shark.miai.common.util.QRCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IProxyAgent;
import com.tigerjoys.shark.miai.agent.ITencentIMAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoStatusEnum;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.shark.miai.service.IVChatVideoService;
import com.tigerjoys.shark.miai.token.TokenService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class ProxyAgentTest extends BaseTestConfig{
private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ITencentIMAgent tencentIMAgent;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	@Autowired
	private IVChatVideoService vChatVideoService;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private IProxyAgent proxyAgent;
	
	
	@Test
	public void testCreateUser() throws Exception{
		proxyAgent.addCpsIncome(80868770094055680L, 10000L);
	}
	
	
	@Test
	public void testCreateNUmUser() throws Exception{
		//proxyAgent.updateCpsStatisticsNum(proxyAgent.getUserOfParents(80868770094055680L));
		
		//proxyAgent.	updateCpsStatisticsNum(getUserOfParents(userid));(80868770094055680L, 10000L);
	}


	
}

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
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.IHomePopAgent;
import com.tigerjoys.shark.miai.inter.entity.HomePopLogEntity;
import com.tigerjoys.shark.miai.service.IHomePopService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


/**
 * 首页弹窗相关业务测试
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class HomePopTest extends BaseTestConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(HomePopTest.class);
	
	@Autowired
	private IHomePopService homePopService;
	
	@Autowired
	private IHomePopAgent homePopAgent;
	
	/**
	 * 数据库增加普通约会内容信息
	 * @throws Exception 
	 */
	@Test
	public void testShowPage() throws Exception {
		long userId = 20295198334583040L;
		String clientId = "b36208944e3afd5f60697d8b30344267";
		ActionResult result = homePopService.showPage(clientId, userId);
		logger.error("json of result:{}",JsonHelper.toJson(result));
	}
	
	@Test
	public void testAddHomePopLog() throws Exception {
		long userId = 20295198334583040L;
		long popId = 1L;
		String clientId = "b36liumane3afd5f60697d8b30344267";
		homePopAgent.addHomePopLog(clientId, popId, userId);
	}
	
	@Test
	public void testQueryTodayHomePopLog() throws Exception {
		long popId = 1L;
		String clientId = "b36liumane3afd5f60697d8b30344267";
		List<HomePopLogEntity> logs = homePopAgent.getHomePopLogList(clientId, popId);
		logger.error("json of result:{}",JsonHelper.toJson(logs));
	}
	
}

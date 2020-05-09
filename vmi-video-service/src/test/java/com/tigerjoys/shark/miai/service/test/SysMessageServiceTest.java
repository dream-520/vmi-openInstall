package com.tigerjoys.shark.miai.service.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.constant.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.inter.contract.IMessageTemplateContract;
import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;
import com.tigerjoys.shark.miai.service.ISystemMessageService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 测试系统消息
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class SysMessageServiceTest extends BaseTestConfig {
	
	@Autowired
	private ISystemMessageService systemMessageService;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IMessageTemplateContract messageTemplateContract;
	
	
	/**
	 * 测试系统消息列表
	 * @throws Exception 
	 */
	@Test
	public void testGetDataList() throws Exception {
		long userId = 24067247928574208L;
		int page = 1;
		boolean isAddReadCount = false;
		if (page == 0 || page == 1) {
			isAddReadCount = true;
		}
		ActionResult result = systemMessageService.messageList(userId, "", page, 20,isAddReadCount,"");
		System.out.println("json of result:" + JsonHelper.toJson(result));
	}
	
	@Test
	public void testAddRedisHash() throws Exception {
		long userId = 10023066L;//10023066 10022609
		//TODO 插入数据到t_message_template
	    MessageTemplateEntity entity  = new MessageTemplateEntity();
	    entity.setCreate_time(new Date());
	    entity.setCreate_adminid(0L);
	    entity.setIntro("刘满测试系统消息");
	    entity.setOpenurl("www.baidu.com");
		entity.setStatus(1);					//状态,1正常，0暂停,-9删除
		entity.setTitle("刘满测试系统消息标题");
		entity.setUpdate_adminid(0L);
		entity.setUpdate_time(new Date());
		entity.setContent("");
		entity.setContent_type(0);
		entity.setPublish_type(1);
		entity.setPublish_time(new Date());
		entity.setOpentype(0);
		entity.setType(0);
		entity.setPush_crowd(0);
		entity.setStatus_adminid(0L);
		entity.setSend(0);
		messageTemplateContract.insert(entity);
		cacheRedis.hincrBy(CommonConst.sys_message, String.valueOf(userId), 1);
	}
	
}

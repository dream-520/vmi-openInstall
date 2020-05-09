package com.tigerjoys.shark.miai.agent.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.ActionRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IDynamicAgent;
import com.tigerjoys.shark.miai.agent.IUserOrdinaryOnlineListAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.service.IAnchorHelperService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

import redis.clients.jedis.Tuple;

/**
 * 测试Ta的主页中相册和动态列表的数据获取
 * @author shiming
 */
public class userOrdinaryOnlineAgentTest extends BaseTestConfig {
	
	@Autowired
	private IAnchorHelperService anchorHelperService;
	
	@Autowired
	private IUserOrdinaryOnlineListAgent userOrdinaryOnlineListAgent;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	
	
	@Test
	public void testGetTopPhoto() {
		cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, 2, "" + 10003);
		cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, 10005, "" + 10004);
		cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, 10006, "" + 10005);
		cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, System.currentTimeMillis()-222, "" + 10006);
		cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, System.currentTimeMillis()-4454, "" + 10007);
		cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, System.currentTimeMillis()-454, "" + 10008);
		cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, System.currentTimeMillis()-4424, "" + 10009);
		Set<String> list = cacheRedis.zrevrangeByScore(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, System.currentTimeMillis(), 0, 0, 100);
		System.out.println(JsonHelper.toJson(list));
		Set<String> newList = new HashSet<>() ;
		newList.add("10004");
		newList.add("10007");
		newList.add("10013");
		newList.removeAll(list);
		
		
		Long ss= new Long(22);
		Long bb= new Long(22);
		System.out.println("kkkk:"+ss.equals(bb));
		System.out.println(cacheRedis.scard("aaaa"));
		System.out.println(JsonHelper.toJson(newList));
		
	}
	
	@Test
	public void testGetTopPhotoNow() {
		
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, -24);
		System.out.println(Tools.getFormatDate(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
		try {
			Set<String> usreSet = userOrdinaryOnlineListAgent.getOnlineUser(100);
			System.out.println("XXXXXXX:"+JsonHelper.toJson(usreSet));
			long ss =System.currentTimeMillis();
			System.out.println("man"+ss);
			System.out.println("max"+((int)ss));
			 Set<Tuple> usreSetList = userOrdinaryOnlineListAgent.getOnlineUserWithScores((int)System.currentTimeMillis(),100);
				System.out.println("XXXXXXX:"+JsonHelper.toJson(usreSetList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testIncrBy() {
		long a = cacheRedis.incrBy("aaa", 0);
		 long recommendRexpire =  cacheRedis.ttl("aaa");
		if(recommendRexpire == -1){
			cacheRedis.expire("aaa", 60*5);
		}
		System.out.println(a);
	}
	
	@Test
	public void testRichUserOnlineListNew() throws Exception {
		ActionResult result = anchorHelperService.richUserOnlineListNew(134831947223466240L,0);
		System.out.println(JsonHelper.toJson(result));
	}
	
	
}

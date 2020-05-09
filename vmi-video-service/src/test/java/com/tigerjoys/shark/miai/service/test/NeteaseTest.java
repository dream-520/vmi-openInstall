package com.tigerjoys.shark.miai.service.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class NeteaseTest  extends BaseTestConfig{
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Test
	public void testGetUserInfo() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("accids", "[\"65418691671490304\",\"65418693063999232\",\"65418693535858432\",\"65418693967871744\",\"65418697029713664\",\"65418697558195968\",\"65418698199924480\",\"65418701460995840\",\"65418701876231936\",\"65418702280982272\"]");
		neteaseAgent.acquireData("https://api.netease.im/nimserver/user/getUinfos.action", map);
	}
	
	@Test
	public void testUpdateUserInfo() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("accid", "65418701460995840");
		params.put("name", "我是你小可爱");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/1.jpg");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418701876231936");
		params.put("name", "蓝色蝴蝶");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/2.jpg");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418702280982272");
		params.put("name", "思思");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/3.jpg");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418697029713664");
		params.put("name", "妖娆姐姐");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/4.PNG");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418697558195968");
		params.put("name", "倾听你的心声");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/5.PNG");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418698199924480");
		params.put("name", "小仙女");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/6.PNG");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418691671490304");
		params.put("name", "等你的小狐狸");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/7.PNG");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418693063999232");
		params.put("name", "女王");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/8.PNG");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418693535858432");
		params.put("name", "粉嫩嫩");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/9.PNG");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
		params.clear();
		params.put("accid", "65418693967871744");
		params.put("name", "棉花糖");
		params.put("icon", "http://cdn.vmi2.liaomeivideo.com/upload/user/2019/07/17/10.PNG");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		
	}
}

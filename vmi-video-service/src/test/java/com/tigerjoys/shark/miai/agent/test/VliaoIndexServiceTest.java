package com.tigerjoys.shark.miai.agent.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.dto.service.UserBaseInfo;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.service.IVliaoIndexService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 测试模拟v聊首页数据获取
 * @author shiming
 */
public class VliaoIndexServiceTest extends BaseTestConfig {
	
	@Autowired
	private IVliaoIndexService vliaoIndexService;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Test
	public void testIndexData() throws Exception {
//		ActionResult result = vliaoIndexService.getAnchors(null);
//		System.err.println(JsonHelper.toJson(result));
//		JSONObject stamp = new JSONObject();
//		stamp.put("audit_time", "2018-09-18 11:41:03");
//		stamp.put("update_time", "2018-09-18 14:09:28");
//		Map<String, String> body = new HashMap<>();
//		body.put("stamp", stamp.toJSONString());
//		result = vliaoIndexService.getAnchors(JsonHelper.toJson(body));
//		System.err.println(JsonHelper.toJson(result));
		
//		ActionResult result = vliaoIndexService.getAnchors(null);
//		System.err.println(JsonHelper.toJson(result));
//		
//		Map<String, String> body = new HashMap<>();
//		body.put("stamp", "2018-09-18 11:41:03");
//		result = vliaoIndexService.getAnchors(JsonHelper.toJson(body));
//		System.err.println(JsonHelper.toJson(result));
		
//		ActionResult result = vliaoIndexService.getAnchors(null);
//		System.err.println(JsonHelper.toJson(result));
		
//		JSONObject stamp = new JSONObject();
//		stamp.put("index", "21");
//		stamp.put("query_time", "1537264200840");
//		Map<String, String> body = new HashMap<>();
//		body.put("stamp", stamp.toJSONString());
//		ActionResult result = vliaoIndexService.getAnchors(JsonHelper.toJson(body));
//		System.err.println(JsonHelper.toJson(result));
		
		ActionResult result = vliaoIndexService.getAnchors(null);
		System.err.println(JsonHelper.toJson(result));
	}
	
	@Test
	public void testSearchData() throws Exception {
		//测试随机数据
		/*
		ActionResult result = vliaoIndexService.searchAnchor(null);
		System.err.println(result);
		if(Tools.isNotNull(result)) {
			System.err.println(result.getData());
			Map<String, Object> data = (Map<String, Object>) result.getData();
			List<UserBaseInfo> list = (List<UserBaseInfo>) data.get("anchors");
			for (UserBaseInfo user : list) {
				System.err.println(user.getNickName());
			}
		}
		*/
		//String body = "{\"word\":\"飞\"}";
		//String body = "{\"word\":\" \"}";
		String body = "{\"word\":\"%\"}";
		ActionResult result = vliaoIndexService.searchAnchor(body);
		System.err.println(result);
		if(Tools.isNotNull(result)) {
			System.err.println(result.getData());
			Map<String, Object> data = (Map<String, Object>) result.getData();
			List<UserBaseInfo> list = (List<UserBaseInfo>) data.get("anchors");
			for (UserBaseInfo user : list) {
				System.err.println(user.getNickName());
			}
		}
		
	}
	
	@Test
	public void testIndexTabData() throws Exception {
		JSONObject stamp = new JSONObject();
		stamp.put("tag", "1");
		ActionResult result = vliaoIndexService.getTabAnchors(JsonHelper.toJson(stamp));
		if(Tools.isNotNull(result)) {
			System.err.println("推荐数据:"+result.getData().toString());
			System.err.println("推荐数据:"+result.getStamp());
			System.err.println("推荐数据:"+result.getNextPage());
			List<UserBaseInfo> list = (List<UserBaseInfo>) result.getData();
			for (UserBaseInfo user : list) {
				System.err.println(user.getNickName());
			}
		}
		
		stamp.clear();
		stamp.put("tag", "2");
		result = vliaoIndexService.getTabAnchors(JsonHelper.toJson(stamp));
		if(Tools.isNotNull(result)) {
			System.err.println("新人数据:"+result.getData().toString());
			System.err.println("新人数据:"+result.getStamp());
			System.err.println("新人数据:"+result.getNextPage());
			List<UserBaseInfo> list = (List<UserBaseInfo>) result.getData();
			for (UserBaseInfo user : list) {
				System.err.println(user.getNickName());
			}
		}
		
		stamp.clear();
		stamp.put("tag", "3");
		result = vliaoIndexService.getTabAnchors(JsonHelper.toJson(stamp));
		if(Tools.isNotNull(result)) {
			System.err.println("三星数据:"+result.getData().toString());
			System.err.println("三星数据:"+result.getStamp());
			System.err.println("三星数据:"+result.getNextPage());
			List<UserBaseInfo> list = (List<UserBaseInfo>) result.getData();
			for (UserBaseInfo user : list) {
				System.err.println(user.getNickName());
			}
			stamp.clear();
			stamp.put("tag", "3");
			stamp.put("stamp", result.getStamp());
			//进一步获取三星数据查看数据是否正确
			result = vliaoIndexService.getTabAnchors(JsonHelper.toJson(stamp));
			if(Tools.isNotNull(result)) {
				System.err.println("三星数据:"+result.getData().toString());
			}
			list = (List<UserBaseInfo>) result.getData();
			for (UserBaseInfo user : list) {
				System.err.println(user.getNickName());
			}
		}
		
		stamp.clear();
		stamp.put("tag", "4");
		result = vliaoIndexService.getTabAnchors(JsonHelper.toJson(stamp));
		if(Tools.isNotNull(result)) {
			System.err.println("四星数据:"+result.getData().toString());
			System.err.println("四星数据:"+result.getStamp());
			System.err.println("四星数据:"+result.getNextPage());
			List<UserBaseInfo> list = (List<UserBaseInfo>) result.getData();
			for (UserBaseInfo user : list) {
				System.err.println(user.getNickName());
			}
		}
		
		stamp.clear();
		stamp.put("tag", "5");
		result = vliaoIndexService.getTabAnchors(JsonHelper.toJson(stamp));
		if(Tools.isNotNull(result)) {
			System.err.println("五星数据:"+result.getData().toString());
			System.err.println("五星数据:"+result.getStamp());
			System.err.println("五星数据:"+result.getNextPage());
			List<UserBaseInfo> list = (List<UserBaseInfo>) result.getData();
			for (UserBaseInfo user : list) {
				System.err.println(user.getNickName());
			}
		}
	}
	
	@Test
	public void testData() throws Exception {
		List<AnchorOnlineEntity> datas = anchorOnlineContract.attentionAnchors("90409165501038848", "2018-12-10 00:00:00");
		System.err.println(datas.toString());
	}
}

package com.tigerjoys.shark.miai.agent.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.shark.miai.BaseSpringBootTests;
import com.tigerjoys.shark.miai.agent.IDynamicAgent;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 测试Ta的主页中相册和动态列表的数据获取
 * @author shiming
 */
public class DynamicAgentTest extends BaseSpringBootTests {
	
	@Autowired
	private IDynamicAgent dynamicAgent;
	
	@Test
	public void testGetTopPhoto() {
		long userId = 8158578022088960L;
		try {
			Map<String, List<String>> map = dynamicAgent.getTopPhoto(userId);
			List<String> bigPaths = map.get("bigPaths");
			List<String> smallPaths = map.get("smallPaths");
			int total1 = smallPaths.size();
			int total2 = bigPaths.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTopDynamic() {
		long userId = 146867685339980L;
		try {
			List<String> paths = dynamicAgent.getTopDynamic(userId);
			int total = paths.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQQ() throws ClientProtocolException, IOException {
		String url = "https://console.tim.qq.com/v4/im_open_login_svc/account_import?usersig=eJxNj11PwjAUhv*K6bXRlpaxkXAxWTXoiMjqyLxpytppdW5N6QQx-nfLMo3n7jxvnvPxBViaXYiybLvGcfdpFJgCCM57rKVqnK60sh5u1JY760oO0RALY7TkwnFs5T9rJ994H3mGCIQIT0L066iD0VZxUbl*KIZ-ln72-ZI*zhdJnq6Do1gtlChovM0THB4u6*Iq7VhaHEmXkbun0IX5qnqINY0hZe11tq5e6m5OTMFEyG5flUGYtjLLJ8k92pd1sNnfyOVsWOb0**lPNMZBQKIoIgP-UHan2wZMz8AIojEa*ft8ge8fjStYNg__&identifier=Web_trtc_01&sdkappid=1400137811&random=1234&contenttype=json";
		String json = "{\"Identifier\":\"Web_trtc_01\",\"Nick\":\"test123\",\"FaceUrl\":\"http://www.qq.com\"}";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity postingString = new StringEntity(json);//json传递
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(post);
		String content = EntityUtils.toString(response.getEntity());
		System.out.println(content);
	}
	
}

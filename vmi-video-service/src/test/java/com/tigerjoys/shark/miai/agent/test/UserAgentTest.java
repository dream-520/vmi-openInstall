package com.tigerjoys.shark.miai.agent.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.NeteaseApiConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserCreatDto;
import com.tigerjoys.shark.miai.agent.utils.CheckSumBuilder;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 用户代理测试
 * @author chengang
 *
 */
public class UserAgentTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private IRedFlowerAgent redFlowerAgent;
	
	
	/**
	 * 测试findById
	 * @throws Exception
	 */
	@Test
	public void findByIdTest() throws Exception {
		UserBO user = userAgent.findById(10001);
		logger.info("{}" , JsonHelper.toJson(user));
		
		//不走缓存了
		user = userAgent.findById(10001);
		logger.info("{}" , JsonHelper.toJson(user));
	}
	
	@Test
	public void insertUserTest() throws Exception {
		UserCreatDto dto = new UserCreatDto();
		dto.setBirthday(new Date());
		dto.setCityid(0L);
		dto.setDegreeid(1);
		dto.setFr(0);
		dto.setNickname("cctv111");
		dto.setOpenid("45sd4asd6as6d4a64dasd1");
		dto.setSex(1);
		dto.setSignature("----");
		UserBO user = userAgent.createUser(dto, null);
		logger.info("{}" , JsonHelper.toJson(user));
	}
	
	
	@Test
	public void testUserTest() throws Exception {
		
//		Map<String, String> header = new HashMap<>();
//		header.put("AppKey", NeteaseApiConst.APP_KEY);
//		header.put("Nonce", NeteaseApiConst.NONCE);
//		header.put("CurTime", curTime);
//		header.put("CheckSum", CheckSumBuilder.getCheckSum(NeteaseApiConst.APP_SECRET, NeteaseApiConst.NONCE, curTime));
//		
		String url="https://console.tim.qq.com/v4/im_open_login_svc/account_import";
		
		Map<String, String> params = new HashMap<>();
		params.put("usersig", "eJxNj9FugjAUhl9l6S3L6BGQzjvn3GjWgQSM46phUrQjFAbViWbvPiQs8Vx*3-nPyX9BMYse0u22OijNdVcLNEMY3Q9YZkJpmUvR9PDroEqpdqNK61pmPNXcarKbRJsVfFA9AxtjsFwCMEpxqmUjeJrr4SCZ9gv-ObnryfsyWdBwkQQ-T-7K9w1v7T2*BHi5ifZKfYRCnQ2TFLHtAWP5SVEzpPv525wJ0X5CQHHBXlkeuFFsdzR5rmzTLL87g6zYMTyvKevGZ1qW15bgWFPXAQxk5EfRtLJSaHaHJhgcmFj4Ouj3D5fIVuE_");
		params.put("identifier", "junming");
		params.put("sdkappid", "1400137811");
		params.put("random", "1234");
		Map<String, String> contentType = new HashMap<>();
		contentType.put("Identifier", "Web_trtc_01");
		contentType.put("Nick", "test123");
		contentType.put("FaceUrl", "http://www.qq.com");
		params.put("contenttype", "json");
		
		url = HttpUtils.generateURL(url, params, ECharset.UTF_8.getName());
		// 执行请求
		ResponseStatus response = HttpUtils.postEntity(url, JsonHelper.toJson(contentType), ECharset.UTF_8, EContentType.APPLICATION_JSON);
		// 打印执行结果
		String json = response.getContent();
		System.out.println(json);
		
	}
	/**
	 * 创建网易用户
	 * @throws Exception
	 */
	@Test
	public void testCreateUser() throws Exception{
		Map<String, String> params = new HashMap<>();
		params.put("accid", "adminVmi");
		params.put("name", "adminVmiName");
		params.put("icon",
				"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506578844&di=6ce7ae3acce7dd07b15c07b1075c0958&imgtype=jpg&er=1&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201406%2F29%2F20140629180728_ktRJ2.jpeg");
		params.put("token", "adminVmiToken");
		JSONObject json = neteaseAgent.createUser(params);
		System.out.println(json.toString());
	}

	@Test
	public void testRedFlower() throws Exception{
		long ss = redFlowerAgent.getRedFlowerBalance(161821264476963072L);
	
		System.out.println("ssss+++===="+ss);
		
	}
	
}

package com.tigerjoys.shark.miai.service.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


/**
 * @author shimng
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class IpTest extends BaseTestConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(IpTest.class);
	

	@Test
	public void testIp() throws Exception {
		Map<String , String> headerMap1 = new HashMap<>();
        headerMap1.put("Host", "ip.didiman.com");
        headerMap1.put("Connection", "Keep-Alive");
        headerMap1.put("User-Agent", "Mozilla/5.0 (Linux; Android 8.0.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36");
        
        ResponseStatus response = HttpUtils.get("http://ip.didiman.com/", ECharset.UTF_8, EContentType.APPLICATION_JSON, headerMap1, null);
        if(Tools.isNotNull(response)){
            String content = response.getContent();
            if(Tools.isNotNull(content)) {
            	logger.info("result of json:{}", JsonHelper.toJson(content));
            	logger.info("result of json:{}", JsonHelper.toJson(content.length()));
            	content = content.replaceAll("\r|\n", "");
            	logger.info("result of json:{}", JsonHelper.toJson(content));
            	logger.info("result of json:{}", JsonHelper.toJson(content.length()));
            }
        }
	}
	
	
}

package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;

import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class KuaiShouAdTest extends BaseTestConfig {
	
	@Test
	public void testKuaiShouAd() throws Exception {
		String callback = "http://ad.partner.gifshow.com/track/activate?callback=SpEEGo2BHz_zB5Dxxc9OPKYGWCUFr4fTYKrEXKUIuyGxhoLqb1NqDoslr6UbD4Kl8lm9TXOkPdonAN219AnrWmpoSLus5cf1Nc0YKnQEl0-VpwlZJe2e9xFOREAGTDL92nQIHkNivGPGoKe3HiwapLC5NrZ5uhv_J56KjqCd4Yiifhw6GmHXRBvn5FJU-oXo26o9kiPevcolxHYsR0d7FOH7pqJ2Lddd3e5fZRJSM-I";
		String url = callback+"&"+"event_type="+2+"&"+"event_time"+System.currentTimeMillis();
		ResponseStatus status = HttpUtils.get(url);
		if(Tools.isNotNull(status)) {
			String content = status.getContent();
			if(Tools.isNotNull(content))
				//1 content:{"result":1,"host-name":"bjzyx-rs1647.zqy"}
				//2 content:{"result":1,"host-name":"bjfk-rs12299.yz02"}
				System.err.println("content:" + content);
		}
	}
}

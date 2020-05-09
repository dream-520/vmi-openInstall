package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;

import com.tigerjoys.shark.miai.agent.constant.PushConstant;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;
import com.xiaomi.xmpush.server.Tracer;

public class XiaoMiPushTest extends BaseTestConfig {

	@Test
	public void testGetPushInfo() throws Exception {
		Tracer tracer = new Tracer(PushConstant.android_a_xiaomi_accountSecret);
		 // 获取一个时间区间内的消息的送达状态, 参数:开始时间, 结束时间, retry次数
	    String messageStatus2 = tracer.getMessageStatus(System.currentTimeMillis() - 10000000, System.currentTimeMillis(), 3);
	    System.out.println(messageStatus2);
	}
}

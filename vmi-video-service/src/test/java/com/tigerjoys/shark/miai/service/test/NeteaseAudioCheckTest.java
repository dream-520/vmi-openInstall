package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.shark.miai.agent.INeteaseAudioCheckAgent;
import com.tigerjoys.shark.miai.agent.INeteasePictureCheckAgent;
import com.tigerjoys.shark.miai.service.INeteasePictureCheck;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class NeteaseAudioCheckTest extends BaseTestConfig {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private INeteaseAudioCheckAgent neteaseAudioCheckAgent;
	
	@Autowired
	private INeteasePictureCheckAgent neteasePictureCheckAgent;
	
	//@Test
	public void testAudioCheck() throws Exception {
		neteaseAudioCheckAgent.check("http://noss.yoyo.liaomeivideo.com/upload/anchor/audio/2019/12/04/1575429070805_48.aac", "http://testvideo.yoyo.liaomeivideo.com/vmi-video-service/third/party/netease/msg/audioResult");
	}
	
	@Test
	public void testPhotoCheck() throws Exception {
		neteasePictureCheckAgent.check("http://cdn.vmi2.liaomeivideo.com/upload/photo/picture/2019/12/11/1575994632090_7700.png", 0.5, 0.5);
	}
}

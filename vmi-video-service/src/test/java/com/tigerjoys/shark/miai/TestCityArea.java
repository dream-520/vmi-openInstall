package com.tigerjoys.shark.miai;

import org.junit.Test;
import org.shark.miai.common.util.AESFieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.shark.miai.agent.dto.AreaDto;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.service.IChannelCheckService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class TestCityArea extends BaseTestConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCityArea.class);

	@Autowired
	private IAppAreaService appAreaService;
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	@Test
	public void testCityArea() throws Exception {
		long province = 0;
		//获取到的广东省的id为  3696
		//AreaDto[] area = appAreaService.getAreasByBaiduCode(131);
		//AreaDto[] area = appAreaService.getAreasByBaiduCode(257);
		//AreaDto[] area = appAreaService.getAreasByBaiduCode(156666);
		AreaDto[] area = appAreaService.getAreasByBaiduCode(0);
		if(Tools.isNotNull(area) && area.length > 0) {
			if(Tools.isNotNull(area[1])) {
				province = area[1].getId();
			}
		}
		LOGGER.error("获取的省份id:"+province);
	}
	
	@Test
	public void testCityAreaServer() throws Exception {
		RequestHeader header = new RequestHeader();
		header.setChannel("test");
		//header.setCityCode("131");
		//header.setCityCode("240");
		header.setCityCode("0");
		//boolean flag = channelCheckService.checkChannel();
		//LOGGER.error("获取的省份id:"+flag);
		System.out.println(AESFieldUtils.encrypt("15001155037"));
	}
	
}

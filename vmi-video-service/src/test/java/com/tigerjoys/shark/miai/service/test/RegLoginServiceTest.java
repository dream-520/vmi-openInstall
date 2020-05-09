package com.tigerjoys.shark.miai.service.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.dto.service.ThirdRegistDto;
import com.tigerjoys.shark.miai.service.IRegLoginService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;
import com.tigerjoys.shark.miai.utils.Helper;

/**
 * 注册登录服务单元测试
 * @author chengang
 *
 */
public class RegLoginServiceTest extends BaseTestConfig {
	
	@Autowired
	private IRegLoginService regLoginService;
	
	private ThirdRegistDto dto = null;
	
	@Before
	public void before() {
		logger.info("====================================================");
		
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		when(httpServletRequest.getRemoteHost()).thenReturn("219.141.236.211");//模拟客户端IP
		
		String randomStr = Helper.getRandom(10);
		
		dto = new ThirdRegistDto();
		dto.setBirthday("1988-12-18");
		dto.setCityCode(131);
		dto.setCityName("北京");
		dto.setFr(1);
		dto.setLat(39.914935D);
		dto.setLng(116.403981D);
		dto.setPhoto(null);
		dto.setSex(1);
		dto.setSignature("谁知悲剧早已注定");
		dto.setNickname("聪明的小坤" + randomStr);
		dto.setOpenid("abcdefg" + randomStr);
		
		RequestHeader header = new RequestHeader();
		header.setClientId("123456asdasdasd");
		header.setOs_type(1);
		
		//初始化线程绑定变量
		BeatContext beat = new BeatContext();
		beat.setRequest(httpServletRequest);
		beat.setHeader(header);
		RequestUtils.bindBeatContextToCurrentThread(beat);
	}
	
	/**
	 * 测试正常的注册
	 * @throws Exception
	 */
	@Test
	public void loginTest() throws Exception {
		logger.info("测试 -> 测试正常的注册~~~~");
		
		regLoginService.login(dto);
	}
	
	/**
	 * 测试没有经纬度传递
	 * @throws Exception
	 */
	@Test
	public void loginNotZuobiaoTest() throws Exception {
		logger.info("测试 -> 测试没有传递过来经纬度的注册~~~~");
		dto.setLat(null);
		dto.setLng(null);
		
		regLoginService.login(dto);
	}
	
	/**
	 * 测试没有传递cityCode根据IP反查城市信息
	 * @throws Exception
	 */
	@Test
	public void loginNotCityCodeTest() throws Exception {
		logger.info("测试 -> 测试没有传递cityCode根据IP反查城市信息的注册~~~~");
		dto.setLat(null);
		dto.setLng(null);
		dto.setCityCode(null);
		dto.setCityName(null);
		
		regLoginService.login(dto);
	}
	
	@After
	public void after() {
		logger.info("====================================================");
	}

}

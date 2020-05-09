package com.tigerjoys.shark.miai.test.persondynamic;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContentContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicFavorContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicFavorEntity;
import com.tigerjoys.shark.miai.service.impl.DynamicServiceImpl;
import com.tigerjoys.shark.miai.test.UserObjectFactory;
import com.tigerjoys.shark.miai.utils.BaseMockitoTestCase;

/**
 * 动态相关的测试用例
 * @author shiming
 *
 */
public class DynamicTest extends BaseMockitoTestCase {
	
	@InjectMocks
	private DynamicServiceImpl dynamicService;
	
	@Mock
	private IUserDynamicContract userDynamicContract;
	
	@Mock
	private IUserDynamicContentContract userDynamicContentContract;
	
	@Mock
	private IUserDynamicFavorContract userDynamicFavorContract;
	
	@Mock
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	@Mock
	private MockHttpServletRequest httpServletRequest;
	
	@Mock
	private IUserAgent userAgent;
	
	private List<UserDynamicEntity> dynamics;
	private UserDynamicEntity userDynamicEntity;

	@Before
	public void before() throws Exception {
		logger.info("测试前进行数据伪装出来!!!!!");
		dynamics = new ArrayList<UserDynamicEntity>();
		UserDynamicEntity entity = new UserDynamicEntity();
		dynamics.add(entity);
		userDynamicEntity = new UserDynamicEntity();
		
		//初始化线程绑定变量
		BeatContext beat = new BeatContext();
		beat.setUserid(10010);
		beat.setUser(UserObjectFactory.createNormalUser(10010, "1988-12-18", "小苹果在东京", true, true));
		beat.setRequest(httpServletRequest);
		RequestUtils.bindBeatContextToCurrentThread(beat);
		logger.info("数据伪装出来完成!!!!!");
	}
	
	@Test
	public void insertDynamic() throws Exception {
		logger.info("测试添加动态的的测试用例--->开始");
		doAnswer(invocation -> {
			return Void.class;
		}).when(userDynamicContract).insert(any());
		
		doAnswer(invocation -> {
			return Void.class;
		}).when(userDynamicContentContract).insert(any());
		logger.info("测试添加动态的的测试用例--->结束");
	}
	
	@Test
	public void addDynamicAudienceNotFound() throws Exception {
		logger.info("测试增加观看数没找到对应动态的测试用例--->开始");
		when(userDynamicContract.findById(anyLong())).thenReturn(null);
		ActionResult result = dynamicService.addDynamicAudience(1001, 1000);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		assertTrue("检测点赞没有对应的动态的情况", result.getCode() == ErrorCodeEnum.db_not_found.getCode());
		logger.info("测试增加观看数没找到对应动态的测试用例--->结束");
	}
	
	@Test
	public void addDynamicAudience() throws Exception {
		logger.info("测试增加观看数的测试用例--->开始");
		when(userDynamicContract.findById(anyLong())).thenReturn(userDynamicEntity);
		when(userDynamicContract.updateByStatement(any(),any())).thenReturn(1);
		ActionResult result = dynamicService.addDynamicAudience(1001, 1000);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		assertTrue("检测点赞没有对应的动态的情况", result.getCode() == 0);
		logger.info("测试增加观看数的测试用例--->结束");
	}
	
	@Test
	public void addDynamicFavorNotFound() throws Exception {
		logger.info("测试点赞没有对应动态的测试用例--->开始");
		when(userDynamicContract.findById(anyLong())).thenReturn(null);
		ActionResult result = dynamicService.addDynamicFavor(1001, 1000);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		assertTrue("检测点赞没有对应的动态的情况", result.getCode() == ErrorCodeEnum.db_not_found.getCode());
		logger.info("测试点赞没有对应动态的测试用例--->结束");
	}
	
	@Test
	public void addDynamicFavorError() throws Exception {
		logger.info("测试重复点赞的测试用例--->开始");
		when(userDynamicContract.findById(anyLong())).thenReturn(userDynamicEntity);
		when(userDynamicFavorContract.count(any(PageModel.class))).thenReturn(1L);
		ActionResult result = dynamicService.addDynamicFavor(1001, 1000);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		assertTrue("检测进行重复点赞的情况", result.getCode() == ErrorCodeEnum.parameter_error.getCode());
		logger.info("测试重复点赞的测试用例--->结束");
	}
	
	@Test
	public void addDynamicFavor() throws Exception {
		logger.info("测试正常点赞的测试用例--->开始");
		when(userDynamicContract.findById(anyLong())).thenReturn(userDynamicEntity);
		when(userDynamicFavorContract.count(any(PageModel.class))).thenReturn(0L);
		doAnswer(invocation -> {
			return Void.class;
		}).when(userDynamicFavorContract).insert(any(UserDynamicFavorEntity.class));
		when(userDynamicContract.updateByStatement(any(),any())).thenReturn(1);
		ActionResult result = dynamicService.addDynamicFavor(1001, 1000);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		assertTrue("检测进行重复点赞的情况", result.getCode() == 0);
		logger.info("测试正常点赞的测试用例--->结束");
	}
	
	@Test
	public void deleteDynamicbyIdNotFound() throws Exception {
		logger.info("测试删除没有对应动态的测试用例--->开始");
		when(userDynamicContract.load(any(PageModel.class))).thenReturn(null);
		ActionResult result = dynamicService.deleteDynamicbyId(1001, 1000);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		assertTrue("测试获取的状态是没有对应的数据的状态", result.getCode() == ErrorCodeEnum.db_not_found.getCode());
		logger.info("测试删除没有对应动态的测试用例--->结束");
	}
	
	@Test
	public void deleteDynamicbyIdError() throws Exception {
		logger.info("测试修改对应动态状态错误的测试用例--->开始");
		when(userDynamicContract.load(any(PageModel.class))).thenReturn(dynamics);
		when(userDynamicContract.update(any())).thenReturn(0);
		ActionResult result = dynamicService.deleteDynamicbyId(1001, 1000);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		assertTrue("测试修改对应的数据库状态错误的效验", result.getCode() == ErrorCodeEnum.db_error.getCode());
		logger.info("测试修改对应动态状态错误的测试用例--->结束");
	}
	
	@Test
	public void deleteDynamicbyId() throws Exception {
		logger.info("测试删除对应动态的测试用例--->开始");
		when(userDynamicContract.load(any(PageModel.class))).thenReturn(dynamics);
		when(userDynamicContract.update(any())).thenReturn(1);
		ActionResult result = dynamicService.deleteDynamicbyId(1001, 1000);
		System.err.println(JsonHelper.toJson(result));		
		assertNotNull(result);
		assertTrue("测试获取的状态是没有对应的数据的状态", result.getCode() == 0);
		logger.info("测试删除对应动态的测试用例--->结束");
	}
}

package com.tigerjoys.shark.miai.test.persondynamic;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.agent.impl.DynamicAgentImpl;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContentContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.test.PersonDynamicFactory;
import com.tigerjoys.shark.miai.utils.BaseMockitoTestCase;

public class PersonDynamicTest extends BaseMockitoTestCase {

	@InjectMocks
	private DynamicAgentImpl dynamicAgent;
	
	@Mock
	private IUserDynamicContract userDynamicContract;
	
	@Mock
	private IUserDynamicContentContract userDynamicContentContract;
	
	@Mock
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	@Before
	public void before() throws Exception {
		logger.info("测试前进行数据伪装出来!!!!!");
		
		logger.info("数据伪装出来完成!!!!!");
	}
	
	@Test
	public void queryTopPhotoNull() throws Exception {
		logger.info("测试没有对应的相册数据的测试用例");
		when(userPhotoResourceContract.load(any(PageModel.class))).thenReturn(null);
		Map<String, List<String>> result = dynamicAgent.getTopPhoto(1L);
		assertNotNull(result);
		assertTrue("测试是否存在大图集合 且集合元素个数为0", result.get("bigPaths").size() == 0);
		assertTrue("测试是否存在小图集合 且集合元素个数为0", result.get("smallPaths").size() == 0);
		logger.info("测试queryTopPhotoNull完成");
	}
	
	@Test
	public void queryTopPhotoLE10() throws Exception {
		logger.info("测试小于10条相册数据的测试用例");
		when(userPhotoResourceContract.load(any(PageModel.class))).thenReturn(PersonDynamicFactory.TopPhotoLE10());
		Map<String, List<String>> result = dynamicAgent.getTopPhoto(1L);
		assertNotNull(result);
		assertTrue("测试是否存在大图集合 且集合元素个数为大于0小于10", result.get("bigPaths").size() > 0 && result.get("bigPaths").size() < 10);
		assertTrue("测试是否存在小图集合 且集合元素个数为大于0小于10", result.get("smallPaths").size() > 0 && result.get("smallPaths").size() < 10);
		assertTrue("测试是否存在小图集合 且集合元素个数为大于0小于10", result.get("bigPaths").size() == result.get("smallPaths").size());
		logger.info("测试queryTopPhotoLE10完成");
	}
	
	@Test
	public void queryTopPhotoEQ10() throws Exception {
		logger.info("测试正好有10条相册的测试用例");
		when(userPhotoResourceContract.load(any(PageModel.class))).thenReturn(PersonDynamicFactory.TopPhotoEQ10());
		Map<String, List<String>> result = dynamicAgent.getTopPhoto(1L);
		assertNotNull(result);
		assertTrue("测试是否存在大图集合 且集合元素个数为10", result.get("bigPaths").size() == 10);
		assertTrue("测试是否存在小图集合 且集合元素个数为10", result.get("smallPaths").size() == 10);
		logger.info("测试queryTopPhotoEQ10完成");
	}
	
	@Test
	public void queryTopPhotoGE10() throws Exception {
		logger.info("测试有超过十条的测试用例");
		when(userPhotoResourceContract.load(any(PageModel.class))).thenReturn(PersonDynamicFactory.TopPhotoGE10());
		Map<String, List<String>> result = dynamicAgent.getTopPhoto(1L);
		assertNotNull(result);
		assertTrue("测试是否存在大图集合 且集合元素个数为大于10", result.get("bigPaths").size() > 10);
		assertTrue("测试是否存在小图集合 且集合元素个数为等于10", result.get("smallPaths").size() == 10);
		logger.info("测试queryTopPhotoGE10完成");
	}
	
	@Test
	public void queryTopDynamicNull() throws Exception {
		logger.info("测试动态数据为空的测试用例");
		when(userDynamicContract.load(any(PageModel.class))).thenReturn(null);
		List<String> result = dynamicAgent.getTopDynamic(1L);
		assertTrue("测试本用户中没有对应的动态数据", result.size() == 0);
		logger.info("测试queryTopDynamicNull完成");
	}
	
	@Test
	public void queryTopDynamicLE4Condition1() throws Exception {
		logger.info("测试获取对应的动态数量不足的情况  1 含有的动态条数不足4条  且每条中都是一张的情况");
		when(userDynamicContract.load(any(PageModel.class))).thenReturn(PersonDynamicFactory.TopDynamicConditionUserDynamicEntity1());
		when(userDynamicContentContract.findById(0)).thenReturn(PersonDynamicFactory.TopDynamicCondition0());
		when(userDynamicContentContract.findById(1)).thenReturn(PersonDynamicFactory.TopDynamicCondition1());
		when(userDynamicContentContract.findById(2)).thenReturn(PersonDynamicFactory.TopDynamicCondition2());
		when(userDynamicContentContract.findById(3)).thenReturn(PersonDynamicFactory.TopDynamicCondition3());
		List<String> result = dynamicAgent.getTopDynamic(1L);
		assertTrue("测试本用户中没有对应的动态数据", result.size() >0 && result.size() < 4);
		assertTrue("测试是否错误的获取视频的链接地址", !(Tools.joinString(result).contains(".mp4")));
		logger.info("测试queryTopDynamicLE4Condition1完成");
	}
	
	@Test
	public void queryTopDynamicLE4Condition2() throws Exception {
		logger.info("测试获取对应的动态数量足够的情况  1 在一条动态中就获取到了四张图像");
		when(userDynamicContract.load(any(PageModel.class))).thenReturn(PersonDynamicFactory.TopDynamicConditionUserDynamicEntity2());
		when(userDynamicContentContract.findById(4)).thenReturn(PersonDynamicFactory.TopDynamicCondition4());
		when(userDynamicContentContract.findById(5)).thenReturn(PersonDynamicFactory.TopDynamicCondition1());
		when(userDynamicContentContract.findById(6)).thenReturn(PersonDynamicFactory.TopDynamicCondition1());
		when(userDynamicContentContract.findById(7)).thenReturn(PersonDynamicFactory.TopDynamicCondition1());
		List<String> result = dynamicAgent.getTopDynamic(1L);
		logger.info("获取到的测试数据结果"+Tools.joinString(result));
		assertTrue("测试本用户中没有对应的动态数据", result.size() == 4);
		assertTrue("测试是否错误的获取视频的链接地址", !(Tools.joinString(result).contains(".mp4")));
		logger.info("测试queryTopDynamicLE4Condition1完成");
	}
	
	
}

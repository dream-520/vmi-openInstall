package com.tigerjoys.shark.miai.test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.agent.service.impl.AppAreaServiceImpl;
import com.tigerjoys.shark.miai.inter.contract.IAppAreaContract;
import com.tigerjoys.shark.miai.utils.AopTargetUtils;

/**
 * Mock创建工厂
 * @author chengang
 *
 */
public class MockFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MockFactory.class);
	
	/**
	 * 替换AppAreaServiceImpl的部分方法
	 * @return IAppAreaService
	 */
	public static IAppAreaService spyAppAreaService(){
		AppAreaServiceImpl spyAppAreaService = spy(AppAreaServiceImpl.class);
		
		try {
			ReflectionTestUtils.setField(AopTargetUtils.getTarget(spyAppAreaService), "appAreaContract", mockAppAreaContract());
			doReturn(null).when(spyAppAreaService).getAreaListByDB(anyLong());
			
			spyAppAreaService.initCityInfo();
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return spyAppAreaService;
	}
	
	/**
	 * mock 城市Contract的 load方法
	 * @return IAppAreaContract
	 * @throws Exception
	 */
	public static IAppAreaContract mockAppAreaContract() {
		IAppAreaContract mockAreaContract = mock(IAppAreaContract.class);
		try {
			when(mockAreaContract.load(any(PageModel.class))).thenReturn(AreaFactory.getAreaEntityList());
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return mockAreaContract;
	}

}

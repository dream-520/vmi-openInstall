package com.tigerjoys.shark.miai.dbtest.testservice;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.service.impl.PhotoServiceImpl;
import com.tigerjoys.shark.miai.utils.BaseMockitoTestCase;

/**
 * 测试的。。不需要看....
 * @author chengang
 *
 */
public class MockTest extends BaseMockitoTestCase {
	
	@InjectMocks
	private PhotoServiceImpl photoService;
	
	@Mock
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	@Before
    public void init() {
		logger.info("start init !!!!!");
    }
	
	@Test(expected=RuntimeException.class)
    public void testCreateRepayPlanException() throws Exception {
		doThrow(new RuntimeException("test excpetion")).when(userPhotoResourceContract).insertAll(any());
		
		List<String> paths = new ArrayList<>();
		ActionResult result = photoService.addPhotoPicture(10001, paths);
		System.err.println(JsonHelper.toJson(result));
		
		assertNotNull(result);
    }

}

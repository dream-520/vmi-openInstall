package com.tigerjoys.shark.miai.service.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.service.ICouponService;
import com.tigerjoys.shark.miai.service.IPropShopService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class PropShopServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PropShopServiceTest.class);

	@Autowired
	private IPropShopService propShopService;
	
	@Test
	public void testGetShopPropList() {
		try {
			logger.info("======testGetShopPropList=====start======");
			
			long userid = 10022588;
			Map<String, Object> t = propShopService.getShopPropList(userid);
			System.out.println(JsonHelper.toJson(t));
			
			logger.info("======testGetShopPropList=====end======");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

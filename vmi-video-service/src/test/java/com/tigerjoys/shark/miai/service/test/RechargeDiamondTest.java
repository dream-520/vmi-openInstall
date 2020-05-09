package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.pay.RechargeDiamondPaid;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class RechargeDiamondTest extends BaseTestConfig {

	@Autowired
	private RechargeDiamondPaid rechargeDiamondPaid;
	
	@Autowired
	private IUserPayActionContract userPayActionContract;
	
	@Test
	public void testRechargeDiamondData() throws Exception {
		UserPayActionEntity entity = userPayActionContract.findById(93845331468419328L);
		if(Tools.isNotNull(entity)) {
			rechargeDiamondPaid.notifyData(entity);
		}
	}
}

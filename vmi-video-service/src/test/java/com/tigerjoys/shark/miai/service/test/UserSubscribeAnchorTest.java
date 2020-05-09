package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.service.IUserSubscribeAnchorService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class UserSubscribeAnchorTest extends BaseTestConfig {

	@Autowired
	private IUserSubscribeAnchorService userSubscribeAnchorService;
	
	@Test
	public void testUserSubscribeAnchorList() throws Exception {
		long userid = 161102421135786240L;
		ActionResult res = userSubscribeAnchorService.getSubscribes(userid);
		System.err.println(JsonHelper.toJson(res));
	}
}

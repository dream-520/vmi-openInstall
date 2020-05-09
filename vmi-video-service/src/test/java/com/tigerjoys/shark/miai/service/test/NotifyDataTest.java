package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.shark.miai.controller.NotifyDataController;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class NotifyDataTest  extends BaseTestConfig {

	@Autowired
	private NotifyDataController notifyDataController;
	
	@Test
	public void testNotifyData() {
		notifyDataController.attachNeteaseMsg(null);
		try {
			Thread.currentThread().sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

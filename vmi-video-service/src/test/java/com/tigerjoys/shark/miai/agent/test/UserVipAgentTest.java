package com.tigerjoys.shark.miai.agent.test;

import java.time.LocalTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IAnchorDefendAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserVipAgent;
import com.tigerjoys.shark.miai.agent.IUserWeekCardAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 测试vip的相关功能
 * @author shiming
 *
 */
public class UserVipAgentTest extends BaseTestConfig {

	@Autowired
	private IUserVipAgent userVipAgent;
	
	@Autowired
	private IUserWeekCardAgent userWeekCardAgent;
	
	@Autowired
	private IAnchorDefendAgent anchorDefendAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	
	@Test
	public void increaseVipDayTest() throws Exception{
		userVipAgent.increaseVipDay(157644262727549184L, 2);
	}
	
	@Test
	public void updateDailyWeekCardDuration() throws Exception{
		userWeekCardAgent.updateDailyWeekCardDuration(160697009626480896L, 4);
	}
	
	@Test
	public void updateLocalTime() throws Exception{
		LocalTime localTime = LocalTime.now();
		 System.out.println(localTime.getHour());
	}
	
	@Test
	public void getWeekCardTotalDuration() throws Exception{
		long totalMinute = userWeekCardAgent.getWeekCardTotalDuration(159805667281010944L,4);
		System.out.println("totalMinute:"+totalMinute);
		
		long cardId = userWeekCardAgent.getDailyWeekCard(159805667281010944L,4);
		
		System.out.println("cardId:"+cardId);
		
		
	}
	
	@Test
	public void anchorDefend() throws Exception{
		UserBO userBO = userAgent.findById(162860191952470272L);
		System.out.println("VIP:"+userBO.vipValue());
		GuardVipCategoryEntity guardList = anchorDefendAgent.getCurrentAnchorDefendByUser(162860191952470272L, 134831734503047424L);
		System.out.println(Tools.isNotNull(guardList)?"guardTure":"false");
		
	}
}

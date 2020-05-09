package com.tigerjoys.shark.miai.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.agent.IAnchorDefendAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendPayDto;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendTopDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipOrderContract;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipOrderEntity;
import com.tigerjoys.shark.miai.service.IAnchorDefendService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class AnchorDefendTest extends BaseTestConfig {
	
	@Autowired
	private IAnchorDefendAgent anchorDefendAgent;
	
	@Autowired
	private IGuardVipCategoryContract guardVipCategoryContract;
	
	@Autowired
	private IGuardVipOrderContract guardVipOrderContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorDefendService anchorDefendService;
	
	@Test
	public void testCreatePayDefend() throws Exception {
		//GuardVipCategoryEntity guard = guardVipCategoryContract.findById(1);
		
		GuardVipCategoryEntity guard = guardVipCategoryContract.findById(2);
		
		Date create_time = new Date();
		long orderid = IdGenerater.generateId();
		//long user_id = 132458211732160768L;
		long user_id = 154745923134619904L;
		long anchorId = 65418697558195968L;
		UserBO user = userAgent.findById(user_id);
		GuardVipOrderEntity t = new GuardVipOrderEntity();
		t.setUser_id(user_id);
		t.setAnchorId(anchorId);
		t.setOrder_id(orderid);
		
		t.setDays(guard.getDays());
		t.setDonor(guard.getDonor());
		t.setIncome(0);
		t.setMobile(user.getMobile());
		t.setMoney(guard.getMoney());
		t.setNickname(user.getNickname());
		t.setPrice(guard.getMoney());
		t.setPrice_id(guard.getId());
		
		t.setStatus(1);
		t.setType(1);
		t.setCreate_time(create_time);
		t.setUpdate_time(create_time);
		guardVipOrderContract.insert(t);
		anchorDefendAgent.buyAnchorDefend(orderid);
	}
	
	@Test
	public void testAnchorTop4Defend() throws Exception {
		long anchorId = 134831734503047424L;
		List<AnchorDefendTopDto> dtos = anchorDefendAgent.anchorTop4Defend(anchorId);
		for (AnchorDefendTopDto dto : dtos) {
			System.err.println("photo:"+dto.getPhoto() +" guardLevel:"+dto.getGuardLevel());
		}
	}

	@Test
	public void testExpiryAnchorDefend() throws Exception {
		long user_id = 145328275512688896L;
		long anchorId = 134831734503047424L;
		List<AnchorDefendPayDto> dtos = anchorDefendAgent.expiryAnchorDefend(user_id, anchorId);
		for (AnchorDefendPayDto dto : dtos) {
			System.err.println("name:"+dto.getName() +" date:"+dto.getDate());
		}
	}
	
	@Test
	public void testgetAnchorDefend() throws Exception {
		anchorDefendService.getAnchorDefend(134831734503047424L);
	}
	
	@Test
	public void testgetUserDefend() throws Exception {
		ActionResult res = anchorDefendService.getUserDefend(66494830169096448L);
		System.err.println(JsonHelper.toJson(res));
	}
	
}

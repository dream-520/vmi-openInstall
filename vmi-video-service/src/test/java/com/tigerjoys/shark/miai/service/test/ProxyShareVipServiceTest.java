package com.tigerjoys.shark.miai.service.test;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.util.RedPackageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.ICouponAgent;
import com.tigerjoys.shark.miai.agent.IProxyAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInvitecodeContract;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInvitecodeEntity;
import com.tigerjoys.shark.miai.service.ICouponService;
import com.tigerjoys.shark.miai.service.IProxyService;
import com.tigerjoys.shark.miai.service.IVipService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class ProxyShareVipServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ProxyShareVipServiceTest.class);

	@Autowired
	private ICouponService couponService;
	
	@Autowired
	private ICouponAgent couponAgent;
	
	@Autowired
	private IProxyService proxyService;
	
	@Autowired
	private IProxyAgent proxyAgent;
	
	@Autowired
	private IVipService vipService;
	
	@Autowired
	private IUserInviteContract userInviteContract;
	
	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	
	
	@Autowired
	private IUserInvitecodeContract userInvitecodeContract;
	
	@Test
	public void queryAllCoupon() {
		try {
			logger.info("======queryAllCoupon=====start======");
			
			ActionResult result=couponService.queryAllCoupon(10003L);
			System.out.println(JsonHelper.toJson(result));		
			logger.info("======queryAllCoupon=====end======");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addCoupon() throws Exception {
		try {
			logger.info("======queryAllCoupon=====start======");
			couponAgent.addUserCoupon(25756923460649216L,0);
			logger.info("======queryAllCoupon=====end======");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryProxy() {
		try {
			logger.info("======queryProxy=====start======");
			
			HashMap<String, Object> result=proxyService.queryProxy(10003L);
			System.out.println(JsonHelper.toJson(result));		
			logger.info("======queryProxy=====end======");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryVip() {
		try {
			logger.info("======queryVip=====start======");
			
			ActionResult result=vipService.queryVip(10003L);
			System.out.println(JsonHelper.toJson(result));		
			logger.info("======queryVip=====end======");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void buyVip() {
		try {
			logger.info("======buyVip=====start======");
			
			ActionResult result=vipService.buyVip(10003L, "12457845412",2, 45);
			System.out.println(JsonHelper.toJson(result));		
			logger.info("======buyVip=====end======");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addInvitation() {
		try {
			logger.info("======buyVip=====start======");
			
			ActionResult result=proxyAgent.addInvitation(19768560177971456L, "123456");
			System.out.println(JsonHelper.toJson(result));		
			logger.info("======buyVip=====end======");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void addInviteCode() throws Exception  {
		int[] num=RedPackageUtil.shuffle(110001,999999);
		ArrayList<UserInvitecodeEntity>list=new ArrayList<UserInvitecodeEntity>();
		for(int re:num){
			UserInvitecodeEntity entity=new UserInvitecodeEntity();
			entity.setInviteCode(""+re);
			entity.setStatus(0);
			list.add(entity);
			if(list.size()>1000){
				userInvitecodeContract.insertAll(list);
				list.clear();
			}
		}
		userInvitecodeContract.insertAll(list);
		System.out.println("运行结束");
		
	}
	
	
	@Test
	public void addInviteSelect() throws Exception  {
		UserInviteEntity entity = userInviteContract.findById(81532247873749248L);
		if(Tools.isNotNull(entity)){
			System.out.println("有值");
		}
	}
	
	@Test
	public void addClear() throws Exception  {
	sysConfigAgent.clearCache();
	}
}

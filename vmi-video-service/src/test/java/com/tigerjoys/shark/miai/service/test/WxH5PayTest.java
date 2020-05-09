package com.tigerjoys.shark.miai.service.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.PayAccessDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class WxH5PayTest extends BaseTestConfig {

	@Autowired
	private IPayAgent payAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Test
	public void testWxH5Pay() throws Exception {
		int mon = new BigDecimal(String.valueOf(0.01)).multiply(new BigDecimal("100")).intValue();
		UserBO user = userAgent.findById(82469753737773312L);
		PayAccessDto access = new PayAccessDto();
		access.setUser_id(user.getUserid());
		access.setNickname(user.getNickname());
		access.setMobile(user.getMobile());
		access.setOrder_id(15777777500L);
		String fm = Tools.formatDouble2PercentToString(mon);
		access.setSubject("[钻石充值：" + fm + "元]");
		access.setDescription("用户账户钻石充值花费：" + fm + "元");
		access.setMoney((long) mon);
		access.setPay_channel(PayChannelEnum.getByCode(4));
		access.setType(PayTypeEnum.recharge_diamond);
		access.setApp_type(1);
		access.setApp_channel("HAIWEI");
		access.setApp_version("2.5.7");
		access.setPackage_name("com.tjhj.miliao");
		AgentResult result = payAgent.payWithWxH5(access);
		if(Tools.isNotNull(result)) {
			System.err.println(result.getCode());
			System.err.println(result.getData().toString());
		}
	}
	
}

package com.tigerjoys.shark.miai.service.test;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.elasticsearch.action.ActionRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.nbs.web.context.UserDetails;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.service.IRechargeGuardVipService;
import com.tigerjoys.shark.miai.service.IRechargeWithdrawalService;
import com.tigerjoys.shark.miai.test.UserObjectFactory;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
  * 充值测试接口
  * @author mouzhanpeng at [2017年9月13日 下午3:06:05] 
  * @since JDK 1.8.0
 */
public class MoneyServiceTest extends BaseTestConfig{
	
	@Autowired
	private IRechargeWithdrawalService rechargeWithdrawalService;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IRechargeGuardVipService rechargeGuardVipService;
	
	
	/**
	 * 支付宝充值
	 * @throws Exception
	 */
	@Test
	public void wrapAliLogs() throws Exception{
		IMocksControl control = EasyMock.createControl();
		RequestHeader header = control.createMock(RequestHeader.class);
		EasyMock.expect(header.getOs_type()).andStubReturn(1);
		EasyMock.expect(header.getVersion()).andStubReturn("1.0.0");
		UserDetails user = control.createMock(UserDetails.class);
		EasyMock.expect(user.getNickname()).andStubReturn("廖驰");
		EasyMock.expect(user.getMobile()).andStubReturn(null);
		EasyMock.expect(user.getUserid()).andStubReturn(10005766L);
		BeatContext context = control.createMock(BeatContext.class);
		EasyMock.expect(context.getUser()).andStubReturn(user);
		EasyMock.expect(context.getHeader()).andStubReturn(header);
		control.replay();
		RequestUtils.bindBeatContextToCurrentThread(context);
		rechargeWithdrawalService.recharge(1, PayChannelEnum.ali.getCode(), 30, 0,null);
		control.verify();
	}
	
	/**
	 * 微信充值
	 * @throws Exception
	 */
	@Test
	public void wrapWxLogs() throws Exception{
		IMocksControl control = EasyMock.createControl();
		RequestHeader header = control.createMock(RequestHeader.class);
		EasyMock.expect(header.getOs_type()).andStubReturn(1);
		EasyMock.expect(header.getVersion()).andStubReturn("1.0.0");
		UserDetails user = control.createMock(UserDetails.class);
		EasyMock.expect(user.getNickname()).andStubReturn("鹏鹏");
		EasyMock.expect(user.getMobile()).andStubReturn("13812349999");
		EasyMock.expect(user.getUserid()).andStubReturn(10002L);
		BeatContext context = control.createMock(BeatContext.class);
		EasyMock.expect(context.getUser()).andStubReturn(user);
		EasyMock.expect(context.getHeader()).andStubReturn(header);
		EasyMock.expect(context.getRequest()).andStubReturn(request);
		control.replay();
		
		RequestUtils.bindBeatContextToCurrentThread(context);
		rechargeWithdrawalService.recharge(3, PayChannelEnum.wx.getCode(), 68,0, null);
		control.verify();
	}
	
	/**
	 * 苹果充值
	 * @throws Exception
	 */
	//@Test
	public void wrapIapLogs() throws Exception{
		IMocksControl control = EasyMock.createControl();
		RequestHeader header = control.createMock(RequestHeader.class);
		EasyMock.expect(header.getOs_type()).andStubReturn(2);
		EasyMock.expect(header.getVersion()).andStubReturn("1.0.0");
		UserDetails user = control.createMock(UserDetails.class);
		EasyMock.expect(user.getNickname()).andStubReturn("鹏鹏");
		EasyMock.expect(user.getMobile()).andStubReturn("13812349999");
		EasyMock.expect(user.getUserid()).andStubReturn(10002L);
		BeatContext context = control.createMock(BeatContext.class);
		EasyMock.expect(context.getUser()).andStubReturn(user);
		EasyMock.expect(context.getHeader()).andStubReturn(header);
		EasyMock.expect(context.getRequest()).andStubReturn(request);
		control.replay();
		RequestUtils.bindBeatContextToCurrentThread(context);
		// has been encoded by base64 
		String receipt = "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";
		//rechargeWithdrawalService.recharge(4, PayChannelEnum.iap.getCode(), 68, 0, receipt);
		control.verify();
	}
	
	/**
	 * 收益分成
	 * @throws Exception
	 */
	@Test
	public void wrapIncome() throws Exception{
		//userIncomeAgent.departIncomeAccount(10002L, 140000, UserIncomeAccountLogTypeEnum.service, "10000099992", "测试", null);
	}
	
	/**
	 * 提现
	 * @throws Exception
	 */
	@Test
	public void wrapWithdrawal() throws Exception{
		userIncomeAgent.withdrawalMoney(30000, 10002L, "鹏鹏", 2, UserIncomeAccountLogTypeEnum.cash_withdrawal, "xioxia", "mjks@sin.cn");
	}
	
	/**
	 * 测试守护列表
	 * @throws Exception
	 */
	@Test
	public void guardVip() throws Exception{
		//初始化线程绑定变量
		BeatContext beat = new BeatContext();
		beat.setUserid(32392032576143616L);
		beat.setUser(UserObjectFactory.createNormalUser());
		beat.setHeader(new RequestHeader());
		beat.getHeader().setOs_type(1);
		
		RequestUtils.bindBeatContextToCurrentThread(beat);
		ActionResult result = rechargeGuardVipService.getNativePriceList(65418693063999232L,1);
		System.out.println(JsonHelper.toJson(result));
	}
	
}

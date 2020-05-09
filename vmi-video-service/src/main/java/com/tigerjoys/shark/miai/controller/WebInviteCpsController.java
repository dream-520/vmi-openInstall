package com.tigerjoys.shark.miai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IProxyAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteCpsStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.UserInviteCpsStatisticsEntity;

/**
 * IOS发现H5页面
 * 
 * @author yangjunming
 *
 */
@Controller
//@TestEncrypt("5KdAVyLCYWMpEV/67SjhMbv69yCD3xE0Jep4y5zNC+mqFGREZlehEWlDMO2HnM1Lp12sEbst2smBpZhfnGtVnlSn0uenvm64WZeGG4G8FvLTyFG9KdXstSsA0sklsqM5JlDg8l3TFJ8ifQAWEggn+9gyYPqUvRapzpDNkoVS5ESKOvYe1D2f9+VLBljm1WeA6W7Exwa56FANQ3ckgMYFnpWBIrsYghm1y+wM6ZJMojdSDpVLL1nPcxSqMmYVk5JP8Z7OLvktbj6ZmUnUKGKePFXLMI/7OKHNp35YYMopTDc4zoXjWWEzHNbryZMKbfGkpNzXabUT8XAyeZnhHiZo1wJhfzeBI1AsSSG+8YkC5W8Xoi/HdDErBnRTPQl44EOxg4DpdlMrjZSFHHF+/Btrs+Ag1ptGtJrhIZHbQJjLDqgIVBofzyOVGDUyaev4ymwqFBd0c6zGFSYSHDIOZ2MENwCgUW68qvFgqo3HUvhDlI4=")
@RequestMapping(value = "/web/invite/cps", produces = Produce.TEXT_HTML)
public class WebInviteCpsController {
	
	@Autowired
	private IUserInviteCpsStatisticsContract userInviteCpsStatisticsContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IProxyAgent proxyAgent;
	
	
	
	/**
	 * 密聊发现页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/incomeList", produces = Produce.TEXT_HTML)
	public String incomeList(Model model) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		
		
		UserInviteCpsStatisticsEntity cpsStatis = userInviteCpsStatisticsContract.findByProperty("userid", userId);
		
		if(Tools.isNull(cpsStatis)){
			cpsStatis = new UserInviteCpsStatisticsEntity();
			cpsStatis.setUserid(userId);
			cpsStatis.setA_num(0);
			cpsStatis.setB_num(0);
			cpsStatis.setC_num(0);
			cpsStatis.setA_income(0L);
			cpsStatis.setB_income(0L);
			cpsStatis.setC_income(0L);
		}
		
		model.addAttribute("cpsStatis" ,cpsStatis);
		model.addAttribute("cpsTotalA" ,Tools.formatDouble2Percent(cpsStatis.getA_income()));
		model.addAttribute("cpsTotalB" ,Tools.formatDouble2Percent(cpsStatis.getB_income()));
		model.addAttribute("cpsTotalC" ,Tools.formatDouble2Percent(cpsStatis.getC_income()));
		model.addAttribute("cpsNum" ,cpsStatis.getA_num()+cpsStatis.getB_num()+cpsStatis.getC_num());
		model.addAttribute("cpsIncome" ,Tools.formatDouble2Percent(cpsStatis.getA_income()+cpsStatis.getB_income()+cpsStatis.getC_income()));
		model.addAttribute("incomeA" ,proxyAgent.getUserInviteCpsIncomeList(userId,1));
		model.addAttribute("incomeB" ,proxyAgent.getUserInviteCpsIncomeList(userId,2));
		model.addAttribute("incomeC" ,proxyAgent.getUserInviteCpsIncomeList(userId,3));
		
		return "miliao_share/InvitationDetails";
	}
	
	
	/**
	 * APK分享下载页
	 * 
	 * @return
	 * @throws Exception
	 */
	@FilterHeader
	@NoLogin
	@RequestMapping(value = "/downUrl/{path}/{inviteCode}", produces = Produce.TEXT_HTML)
	public String userShareApk( @PathVariable String path,@PathVariable String inviteCode ,Model model)
			throws Exception {
		model.addAttribute("inviteCode", inviteCode);
		model.addAttribute("appKey", "d8ecyr");
		model.addAttribute("appName",  Const.getNewAppName(path));
		return "miliao_share/privateChat";

	}
	
	
}

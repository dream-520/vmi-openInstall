package com.tigerjoys.shark.miai.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.TestEncrypt;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.service.IUserSignService;

/**
 * 用户签到功能接口
 * 
 * @author lipeng
 *
 */
@Controller
//@TestEncrypt("5KdAVyLCYWMpEV/67SjhMbv69yCD3xE0Jep4y5zNC+mqFGREZlehEWlDMO2HnM1Lp12sEbst2smBpZhfnGtVnh2MWnFTaAeYWs45M8TofdFClScZDqsrZDepZddIsnn8WXyXrRbrygUCNsLpl2pFIMI7Eq3cFaBhqkrxVcwElSzEXaYNzzjVGd81bSQj2H00f2C1ggXPiyLkLIGqac3aegpq5NE+HBtgw23BjN/vkq+fFnn3GOt1NydSpuhzX4tE9JT8IQID8VQj4zmUUm5mGHU6qnYHWOTsqZDk4+2mOsXRjzWV7pYLASo7RuNCHmRMFRjqDnBOwPnQksFzhPcYw05SYZpjgi5XW13O8fjs0L2sp0OzlPSJ5pbn+jgIoXL/2AmDR3Jrc5D7SU6TJ8QIprhj+OEtfqPL5GtTzTD9lALO7LMxan8gEGCkMsajWmqrnx1HEwf6N+LnDk83uN+YnunC0U+z3X574mvyQqcUAtg=")
@RequestMapping(value = "/api")
public class UserSignController extends BaseController {

	@Autowired
	private IUserSignService userSignService;

	/**
	 * 每个web请求都会自带encrypt到页面上
	 * @return String
	 */
	@ModelAttribute("encrypt")
	public String addEncrypt() {
		return RequestUtils.getCurrent().getHeaderEncrypt();
	}
	
	/**
	 * 签到页面
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("sign")
	@NoSign
	@RequestMapping(value = "/sign/init", produces = Produce.TEXT_HTML)
	public String signInit(HttpServletRequest request, Model model) throws Exception {
		BeatContext beat = RequestUtils.getCurrent();
		// 验证请求头
		model.addAttribute("encrypt", beat.getHeaderEncrypt());
		userSignService.initSign(model);
		return "activity/sign/sign";
	}
	
	/**
	 * 今天签到
	 * 
	 * @throws Exception
	 */
	@UserClientService("sign")
	@NoSign
	@RequestMapping(value = "/sign/signing", method = RequestMethod.POST)
	public void signing(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObjectResult = userSignService.signing();
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonObjectResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 补签页面
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("sign")
	@NoSign
	@RequestMapping(value = "/sign/supplement", produces = Produce.TEXT_HTML)
	public String supplement(HttpServletRequest request, Model model) throws Exception {
		BeatContext beat = RequestUtils.getCurrent();
		// 验证请求头
		model.addAttribute("encrypt", beat.getHeaderEncrypt());
		userSignService.supplement(model);
		return "activity/sign/sign2";
	}
	
	/**
	 * 签到日历初始化
	 * 
	 * @throws Exception
	 */
	@UserClientService("sign")
	@NoSign
	@RequestMapping(value = "/sign/calendarInit", method = RequestMethod.POST)
	public void supplementInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObjectResult = userSignService.calendarInit();
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonObjectResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 购买签到卡
	 * 
	 * @throws Exception
	 */
	@UserClientService("sign")
	@NoSign
	@RequestMapping(value = "/sign/buySignCard", method = RequestMethod.POST)
	public void buySignCard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObjectResult = userSignService.buySignCard();
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonObjectResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 补签
	 * 
	 * @throws Exception
	 */
	@UserClientService("sign")
	@NoSign
	@RequestMapping(value = "/sign/suppleDays", method = RequestMethod.POST)
	public void suppleDays(HttpServletRequest request, HttpServletResponse response ,@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		String day = json.getString("addDay");
		JSONObject jsonObjectResult = userSignService.suppleDays(day);
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonObjectResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

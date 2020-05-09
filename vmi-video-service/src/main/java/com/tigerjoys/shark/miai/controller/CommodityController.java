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
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.TestEncrypt;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.service.ICommodityService;

/**
 * 领取商品相关接口
 * 
 * @author lipeng
 *
 */
@Controller
//@TestEncrypt("5KdAVyLCYWMpEV/67SjhMbv69yCD3xE0Jep4y5zNC+mqFGREZlehEWlDMO2HnM1Lp12sEbst2smBpZhfnGtVnh2MWnFTaAeYWs45M8TofdFClScZDqsrZDepZddIsnn8WXyXrRbrygUCNsLpl2pFIMI7Eq3cFaBhqkrxVcwElSzEXaYNzzjVGd81bSQj2H00f2C1ggXPiyLkLIGqac3aegpq5NE+HBtgw23BjN/vkq+fFnn3GOt1NydSpuhzX4tE9JT8IQID8VQj4zmUUm5mGHU6qnYHWOTsqZDk4+2mOsXRjzWV7pYLASo7RuNCHmRMFRjqDnBOwPnQksFzhPcYw05SYZpjgi5XW13O8fjs0L2sp0OzlPSJ5pbn+jgIoXL/2AmDR3Jrc5D7SU6TJ8QIprhj+OEtfqPL5GtTzTD9lALO7LMxan8gEGCkMsajWmqrnx1HEwf6N+LnDk83uN+YnunC0U+z3X574mvyQqcUAtg=")
@RequestMapping(value = "/api")
public class CommodityController extends BaseController {

	@Autowired
	private ICommodityService commodityService;

	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;

	/**
	 * 每个web请求都会自带encrypt到页面上
	 * @return String
	 */
	@ModelAttribute("encrypt")
	public String addEncrypt() {
		return RequestUtils.getCurrent().getHeaderEncrypt();
	}
	
	/**
	 * 领取礼包列表页面
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("commodity")
	@NoSign
	@RequestMapping(value = "/commodity/getCommodityList", produces = Produce.TEXT_HTML)
	public String getCommodityList(HttpServletRequest request, Model model) throws Exception {
		BeatContext beat = RequestUtils.getCurrent();
		// 验证请求头
		model.addAttribute("encrypt", beat.getHeaderEncrypt());
		commodityService.getCommodityList(model);
		
		return "goods/goodsList";
	}
	
	/**
	 * 领取说明页面
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("commodity")
	@NoSign
	@FilterHeader
	@RequestMapping(value = "/commodity/rule", produces = Produce.TEXT_HTML)
	public String getCommodityList(Model model) throws Exception {
		return "goods/goodsRule";
	}
	
	/**
	 * 领取商品记录
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("commodity")
	@NoSign
	@RequestMapping(value = "/commodity/getRecord", produces = Produce.TEXT_HTML)
	public String getRecord(HttpServletRequest request, Model model) throws Exception {
		BeatContext beat = RequestUtils.getCurrent();
		// 验证请求头
		model.addAttribute("encrypt", beat.getHeaderEncrypt());
		commodityService.getRecord(model);
		return "goods/goodsRecord";
	}
	
	/**
	 * 领取商品豪礼列表
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("commodity")
	@NoSign
	@RequestMapping(value = "/commodity/getCommodityListOfGroup", produces = Produce.TEXT_HTML)
	public String getCommodityListOfGroup(HttpServletRequest request, Model model) throws Exception {
		BeatContext beat = RequestUtils.getCurrent();
		// 验证请求头
		model.addAttribute("encrypt", beat.getHeaderEncrypt());
		long id = Tools.parseLong(request.getParameter("id"));
		long group_id = Tools.parseLong(request.getParameter("group_id"));
		if (id==0 && group_id==0) {
			return "error";
		}
		commodityService.getCommodityListOfGroup(group_id, model);
		model.addAttribute("relationship_id", id);
		return "goods/goodsDetail";
	}
	
	/**
	 * 领取商品
	 * 
	 * @throws Exception
	 */
	@UserClientService("sign")
	@NoSign
	@RequestMapping(value = "/commodity/geting", method = RequestMethod.POST)
	public void geting(HttpServletRequest request, HttpServletResponse response ,@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long id = json.getLongValue("id");
		long relationship_id = json.getLongValue(("relationship_id"));
		JSONObject jsonObjectResult = commodityService.geting(id , relationship_id);
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonObjectResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}

package com.tigerjoys.shark.miai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.service.IProxyService;

/**
 *	vip控制类
 * @author yangjunming
 *
 */
@Login
@Controller
@RequestMapping(value="/api/proxy" , method=RequestMethod.POST , produces=Produce.TEXT_JSON)
public class ProxyController extends BaseController {
	
	@Autowired
	private IProxyService proxyService;
	
	/**
	 *获取人员明细统计
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/personnelList",method=RequestMethod.POST)
	@Login
	public @ResponseBody ActionResult personnelList(@RequestBody String body) throws Exception {
		BeatContext context= RequestUtils.getCurrent();
		Long userId=context.getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		long stamp = json.getLong("stamp");
		int pageSize = json.getIntValue("pageSize");
		if(pageSize>10 || pageSize == 0){
			pageSize = 10;
		}
		return proxyService.queryPersonnelMapping(userId, pageSize, stamp);
		
	}
	
	/**
	 *获取人员分类明细
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/personnelDesc",method=RequestMethod.POST)
	@Login
	public @ResponseBody ActionResult personnelDescList(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long mappingid = json.getLong("mappingid");
		long stamp = json.getLong("stamp");
		int pageSize = json.getIntValue("pageSize");
		if(pageSize>10 || pageSize == 0){
			pageSize = 10;
		}
		return proxyService.queryPersonnelDesc(mappingid, pageSize, stamp);
	}
	
	/**
	 *获取分成明细统计
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dividedList",method=RequestMethod.POST)
	@Login
	public @ResponseBody ActionResult dividedList(@RequestBody String body) throws Exception {
		BeatContext context= RequestUtils.getCurrent();
		Long userId=context.getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		long stamp = json.getLong("stamp");
		int pageSize = json.getIntValue("pageSize");
		if(pageSize>10 || pageSize == 0){
			pageSize = 10;
		}
		return proxyService.queryDividedMapping(userId, pageSize, stamp);
		
	}
	
	/**
	 *获取分成分类明细
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dividedDesc",method=RequestMethod.POST)
	@Login
	public @ResponseBody ActionResult dividedDescList(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long mappingid = json.getLong("mappingid");
		long stamp = json.getLong("stamp");
		int pageSize = json.getIntValue("pageSize");
		if(pageSize>10 || pageSize == 0){
			pageSize = 10;
		}
		return proxyService.queryDividedDesc(mappingid, pageSize, stamp);
	}
	
}

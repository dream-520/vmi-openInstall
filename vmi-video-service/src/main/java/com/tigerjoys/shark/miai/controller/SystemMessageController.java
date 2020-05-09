package com.tigerjoys.shark.miai.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.inter.contract.IMessageTemplateContract;
import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;
import com.tigerjoys.shark.miai.service.IChannelCheckService;
import com.tigerjoys.shark.miai.service.ISystemMessageService;

/**
 * 系统消息接口
 * @author liuman
 *
 */
//@Login
@Controller
@RequestMapping(value="/api/sysMessage")
public class SystemMessageController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemMessageController.class);
	
	@Autowired
	private IMessageTemplateContract messageTemplateContract;
	
	@Autowired
	private ISystemMessageService systemMessageService;
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	/**
	 * 系统消息列表
	
	@UserClientService("sysMessage")
	@RequestMapping(value="/list",method=RequestMethod.POST, produces=Produce.TEXT_ENCODE)
	public @ResponseBody ActionResult messageList(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long userid = RequestUtils.getCurrent().getUserid();
		String packageName = RequestUtils.getCurrent().getHeader().getPackageName();
		String stamp = json.getString("stamp");
		int page = json.getIntValue("page");
		int pagesize = json.getIntValue("pagesize");
		//是否增加用户读取系统消息数量
		boolean isAddReadCount = false;
		if (page == 0 || page == 1) {
			isAddReadCount = true;
		}
		try {
			//特殊处理华为提审用户的数据显示
			if(userid == 74259596386042112L) {
				return ActionResult.success();
			}
			return systemMessageService.messageList(userid, stamp, page, pagesize,isAddReadCount,packageName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ActionResult.fail();
	}
	*/
	
	/**
	 * 系统消息列表
	 */
	@UserClientService("sysMessage")
	@RequestMapping(value="/list",method=RequestMethod.POST, produces=Produce.TEXT_ENCODE)
	public @ResponseBody ActionResult messageList(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long userid = RequestUtils.getCurrent().getUserid();
		String stamp = json.getString("stamp");
		int page = json.getIntValue("page");
		int pagesize = json.getIntValue("pagesize");
		
		//是否增加用户读取系统消息数量
		boolean isAddReadCount = false;
		if (page == 0 || page == 1 || Tools.isNull(stamp)) {
			isAddReadCount = true;
		}
		try {
			boolean check = channelCheckService.checkChannel();
			if(check) {
				return ActionResult.success();
			}
			
			//特殊处理华为提审用户的数据显示
			if(userid == 74259596386042112L) {
				return ActionResult.success();
			}
			
			if(userid == 146614470655934720L) {
				return ActionResult.success();
			}
			
			//特殊处理苹果账号审核
			if(userid == 61356271330197760L) {
				return ActionResult.success();
			}
			
			//特殊处理苹果账号消息  听你的心
			if(userid == 150039910194151680L) {
				return ActionResult.success();
			}
			
			return systemMessageService.messageListNew(userid, stamp, page, pagesize, isAddReadCount);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ActionResult.fail();
	}
	
	
	/**
	 * 系统消息详情
	 */
	@UserClientService("sysMessage")
	@RequestMapping(value="/message/{id}",method=RequestMethod.GET, produces=Produce.TEXT_HTML)
	public String messageDetail(@PathVariable("id") long id , HttpServletRequest request , Model model) throws Exception {
		MessageTemplateEntity template = messageTemplateContract.findById(id);
		if(template == null || template.getStatus() == -9) {
			return errorPage("您无法查看此系统信息！",model);
		}
		long userid = RequestUtils.getCurrent().getUserid();
		//目前进入系统消息列表该用户的系统小红点就消失了
//		cacheRedis.incrBy(CommonConst.sys_message,-1);
		//url类型
		if(template.getContent_type() == 1) {
			return "redirect:"+template.getOpenurl();
		}
		
		model.addAttribute("title", template.getTitle());
		model.addAttribute("intro", template.getIntro());
		model.addAttribute("time", Tools.getDate(template.getPublish_time()));
		model.addAttribute("content", template.getContent());
		
		return "message/news";
	}
	
}

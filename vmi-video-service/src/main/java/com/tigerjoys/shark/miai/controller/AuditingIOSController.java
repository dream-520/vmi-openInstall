package com.tigerjoys.shark.miai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.dto.service.AuditingIOSDto;
import com.tigerjoys.shark.miai.service.IChannelCheckService;

/**
 * 处理ios审核
 * @author shiming
 *
 */
@Controller
@RequestMapping(value="/api/ios/auditing", produces=Produce.TEXT_ENCODE)
public class AuditingIOSController {

	private static final Logger logger = LoggerFactory.getLogger(ConfController.class);
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.SYS_CONFIG_CACHE)
	private CacheRedis cacheRedis;
	
	/*
	@NoLogin
	@RequestMapping(value="/get")
	public @ResponseBody ActionResult getState() throws Exception {
		AuditingIOSDto dto = new AuditingIOSDto();
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		long userid = 0;
		if(Tools.isNotNull(header)) {
			userid = header.getUserid();
		}
		//设置初始默认值
		dto.setShow1(0);
		dto.setShow2(1);
		//处理逻辑  提审期间 
		boolean check = channelCheckService.checkChannel();
		if(check) {
			dto.setCan(0);
			//审核状态下  获取对应审核账号下的数据
			if(userid > 0) {
				String key = "auditing_" + userid;
				JSONObject json = cacheRedis.getJSONObject(key);
				if(Tools.isNotNull(json)) {
					Integer show1 = json.getInteger("show1");
					Integer show2 = json.getInteger("show2");
					if(Tools.isNotNull(show1)) {
						dto.setShow1(show1);
					}
					if(Tools.isNotNull(show2)) {
						dto.setShow2(show2);
					}
				} else {
					//如果没有对应的值  需要进行设置一下默认值
					cacheRedis.setJson(key, JsonHelper.toJson(dto));
				}
			} 
		} else {
			dto.setCan(1);
			//非审核状态下  检测是否是提审账号   提审账号显示对应的数据
			if(userid == 61356271330197760L) {
				//获取对应的提审账号的数据
				String key = "auditing_" + userid;
				JSONObject json = cacheRedis.getJSONObject(key);
				if(Tools.isNotNull(json)) {
					Integer show1 = json.getInteger("show1");
					Integer show2 = json.getInteger("show2");
					if(Tools.isNotNull(show1)) {
						dto.setShow1(show1);
					}
					if(Tools.isNotNull(show2)) {
						dto.setShow1(show2);
					}
				} else {
					//如果没有对应的值  需要进行设置一下默认值
					cacheRedis.setJson(key, JsonHelper.toJson(dto));
				}
			} else {
				//非提审账号显示对应的提审数据                后期代码控制的地方    
				dto.setShow1(1);
				dto.setShow2(1);
			}
		}
		logger.info("当前用户id:"+userid +" 对应的下发参数为:"+JsonHelper.toJson(dto));
		return ActionResult.success(dto);
	}
	
	@NoLogin
	@RequestMapping(value="/set")
	public @ResponseBody ActionResult setState(@RequestBody String body) throws Exception {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		long userid = 0;
		if(Tools.isNotNull(header)) {
			userid = header.getUserid();
		}
		JSONObject json = JsonHelper.toJsonObject(body);
		Integer show1 = json.getInteger("show1");
		Integer show2 = json.getInteger("show2");
		AuditingIOSDto dto = new AuditingIOSDto();
		if(Tools.isNotNull(show1)) {
			dto.setShow1(show1);
		} 
		if(Tools.isNotNull(show2)) {
			dto.setShow2(show2);
		}
		
		logger.info("当前用户id:"+userid +" 对应的存储参数为:"+JsonHelper.toJson(dto));
		
		boolean check = channelCheckService.checkChannel();
		if(check) {
			//在提审期间的用户 可以进行随意的修改对应的状态
			if(userid > 0) {
				String key = "auditing_" + userid;
				cacheRedis.setJson(key, JsonHelper.toJson(dto));
			}
		} else {
			//非审核期间需要单独存储 审核账号的数据
			if(userid == 61356271330197760L) {
				String key = "auditing_" + userid;
				cacheRedis.setJson(key, JsonHelper.toJson(dto));
			}
		}
		return ActionResult.success();
	}
	*/
	
	@NoLogin
	@RequestMapping(value="/get")
	public @ResponseBody ActionResult getState() throws Exception {
		AuditingIOSDto dto = new AuditingIOSDto();
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		long userid = 0;
		if(Tools.isNotNull(header)) {
			userid = header.getUserid();
		}
		//设置初始默认值
		dto.setShow1(1);
		dto.setShow2(1);
		//处理逻辑  提审期间 
		boolean check = channelCheckService.checkChannel();
		if(check) {
			dto.setCan(1);
			if(Tools.isNotNull(header)) {
				if("ios_miyou".equals(header.getChannel())) {
					dto.setShow1(0);
				} else if("ios_duidui".equals(header.getChannel())) {
					dto.setShow1(1);
				}
			}
			
			//审核状态下  获取对应审核账号下的数据
			if(userid > 0) {
				String key = "auditing_" + userid;
				JSONObject json = cacheRedis.getJSONObject(key);
				if(Tools.isNotNull(json)) {
					Integer show1 = json.getInteger("show1");
					Integer show2 = json.getInteger("show2");
					if(Tools.isNotNull(show1)) {
						dto.setShow1(show1);
					}
					if(Tools.isNotNull(show2)) {
						dto.setShow2(show2);
					}
				} else {
					//如果没有对应的值  需要进行设置一下默认值
					cacheRedis.setJson(key, JsonHelper.toJson(dto));
				}
			} 
		} else {
			//提审通过以后  默认显示
			dto.setCan(1);
			dto.setShow1(1);
			dto.setShow2(1);
			if(userid > 0) {
				String key = "auditing_" + userid;
				JSONObject json = cacheRedis.getJSONObject(key);
				if(Tools.isNotNull(json)) {
					Integer show1 = json.getInteger("show1");
					Integer show2 = json.getInteger("show2");
					if(Tools.isNotNull(show1)) {
						dto.setShow1(show1);
					}
					if(Tools.isNotNull(show2)) {
						dto.setShow2(show2);
					}
				} else {
					//如果没有对应的值  需要进行设置一下默认值
					cacheRedis.setJson(key, JsonHelper.toJson(dto));
				}
			}
		}
		logger.info("当前用户id:"+userid +" 对应的下发参数为:"+JsonHelper.toJson(dto));
		return ActionResult.success(dto);
	}
	
	@NoLogin
	@RequestMapping(value="/set")
	public @ResponseBody ActionResult setState(@RequestBody String body) throws Exception {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		long userid = 0;
		if(Tools.isNotNull(header)) {
			userid = header.getUserid();
		}
		JSONObject json = JsonHelper.toJsonObject(body);
		Integer show1 = json.getInteger("show1");
		Integer show2 = json.getInteger("show2");
		AuditingIOSDto dto = new AuditingIOSDto();
		if(Tools.isNotNull(show1)) {
			dto.setShow1(show1);
		} 
		if(Tools.isNotNull(show2)) {
			dto.setShow2(show2);
		}
		
		logger.info("当前用户id:"+userid +" 对应的存储参数为:"+JsonHelper.toJson(dto));
		if(userid > 0) {
			String key = "auditing_" + userid;
			cacheRedis.setJson(key, JsonHelper.toJson(dto));
		}
		return ActionResult.success();
	}
	
}

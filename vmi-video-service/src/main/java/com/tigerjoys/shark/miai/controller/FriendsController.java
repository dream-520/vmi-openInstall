package com.tigerjoys.shark.miai.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserFriendAgent;
import com.tigerjoys.shark.miai.dto.service.FriendListResultDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.service.IFriendsService;

/**
 * 好友相关接口
 * @author shiming
 */
@Login
@Controller
@RequestMapping(value="/api/friends" , produces=Produce.TEXT_ENCODE)
public class FriendsController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IFriendsService friendsService;
	
	@Autowired
	private IUserFriendAgent userFriendAgent;
	
	/**
	 * 关注状态变化
	 */
	@UserClientService("friend")
	@RequestMapping(value = "/attention" , method=RequestMethod.POST)
	@ResponseBody
	public ActionResult attention(@RequestBody String body , HttpServletRequest request) throws Exception {
		BeatContext beatContext = RequestUtils.getCurrent();
		long userid = beatContext.getUserid();
		if(userid==0) {
			return ActionResult.fail();
		}
		JSONObject json = JsonHelper.toJsonObject(body);
		Long friendid = json.getLong("userid");
		long oldfansCount = userFriendAgent.findFriendUserCount(friendid);
		if(Tools.isNull(friendid) || friendid.longValue() <= 0){
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		//多加一个限制条件  自己不能关注自己的问题
		if(friendid.equals(userid)){
			return ActionResult.fail(ErrorCodeEnum.attention_friends_error);
		}
		//1.关注 0.取消
		Integer state = json.getInteger("state"); 
		if(Tools.isNull(state)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		Map<String, Object> map = friendsService.attentionUser(userid,friendid,state);
		int code = (int) map.get("code");
		if(code == 1){
			int status = (int) map.get("status");
			Map<String, Object> result = new HashMap<>();
			if(status > 1){
				result.put("status", 1);
			} else {
				result.put("status", 0);
			}
			//result.put("fansCount", map.get("fansCount"));
			result.put("fansCount", oldfansCount*9);
			return ActionResult.success(result);
		}
		return ActionResult.fail();
	}
	
	/**
	 * 关注状态变化
	 */
	@UserClientService("friend")
	@RequestMapping(value = "/attentionUser" , method=RequestMethod.POST)
	@ResponseBody
	public ActionResult attentionUser(@RequestBody String body , HttpServletRequest request) throws Exception {
		BeatContext beatContext = RequestUtils.getCurrent();
		long userid = beatContext.getUserid();
		if(userid==0) {
			return ActionResult.fail();
		}
		JSONObject json = JsonHelper.toJsonObject(body);
		Long friendid = json.getLong("userid");
		if(Tools.isNull(friendid) || friendid.longValue() <= 0){
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		//多加一个限制条件  自己不能关注自己的问题
		if(friendid.equals(userid)){
			return ActionResult.fail(ErrorCodeEnum.attention_friends_error);
		}
		//1.关注 0.取消
		Integer state = json.getInteger("state"); 
		if(Tools.isNull(state)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		Map<String, Object> map = friendsService.attentionUser(userid,friendid,state);
		int code = (int) map.get("code");
		if(code == 1){
			Map<String, Object> result = new HashMap<>();
			result.put("status", map.get("status"));
			result.put("id", map.get("id"));
			return ActionResult.success(result);
		}
		return ActionResult.fail();
	}
	
	/**
	 * 好友列表处理
	 */
	@UserClientService("friend")
	@RequestMapping(value = "/friendList" , method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getFriendList(@RequestBody String body , HttpServletRequest request) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		String stamp =  json.getString("stamp");
		int pageSize = 20;
		long lastUserFrId = 0;
		if(Tools.isNotNull(stamp)){
			lastUserFrId = Long.parseLong(stamp);
		}
		//1.好友2.关注 3.粉丝
		Integer type =  json.getInteger("type");
		if(Tools.isNull(type)){
			logger.error("relationStatus==传入参数为空");
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		Long userid = json.getLong("userid");
		if(Tools.isNull(userid) || userid.longValue() == 0){
			logger.error("relationStatus==传入参数为空");
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		
		List<FriendListResultDto> resultList = friendsService.getFriendList(userid,lastUserFrId,pageSize,type);
		if(Tools.isNotNull(resultList) && resultList.size() > 0) {
			int total = resultList.size();
			if(total > pageSize){
				resultList = resultList.subList(0, pageSize);
			}
			return ActionResult.success(resultList, resultList.get(resultList.size()-1).getId(), total > pageSize);
		}
		return ActionResult.success(resultList,"",false);
	}
	
	/**
	 * 查询好友关系状态
	 * @return ActionResult
	 * @throws Exception
	 */
	@UserClientService("friend")
	@RequestMapping(value = "/relationStatus" , method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getRelationStatus(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		Long friendid = json.getLong("userid");
		if(Tools.isNull(friendid) || friendid.longValue() == 0){
			logger.error("relationStatus==传入参数为空");
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		BeatContext beatContext = RequestUtils.getCurrent();
		long userid = beatContext.getUserid();
		if(userid==0){
			logger.error("relationStatus==获取本地userid失败");
			return ActionResult.fail();
		}
		Map<String, Object> result = new HashMap<>();
		result.put("status", friendsService.relationStatus(userid,friendid));
		return ActionResult.success(result);
	}
	
}

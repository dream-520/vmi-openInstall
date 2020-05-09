package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.dto.VChatVideoTCPDto;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoTCPTypeEnum;
import com.tigerjoys.shark.miai.annotations.WaiterActionOnline;
import com.tigerjoys.shark.miai.service.IVChatVideoYXService;

/**
 * 网易视频聊
 * @author yangjunming
 *
 */
@Controller
@RequestMapping(value = "/api/vchatYX", method=RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class VChatVideoYXController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IVChatVideoYXService vChatVideoYXService;

	@Autowired
	private INeteaseAgent neteaseAgent;
	
	
	/**
	 * 获对视频通话对方信息
	 * @return
	 * @throws Exception
	 */
	@Login
	@UserClientService("vchatYX")
	@RequestMapping(value = "/dialing", method=RequestMethod.POST)
	public @ResponseBody ActionResult dialing(@RequestBody String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long toUserId = json.getLong("otherId");
		Integer sponsor = json.getInteger("sponsor");
		int avType = json.getIntValue("avType");
		if(avType == 0){
			avType = 2;
		}
		
		return vChatVideoYXService.getDialing(userId, toUserId,sponsor,avType);
	}
	
	
	/**
	 * 视频通话支付接口
	 * @return
	 * @throws Exception
	 */
	@Login
	@WaiterActionOnline
	@RequestMapping(value = "/payOrderNew", method=RequestMethod.POST)
	public @ResponseBody ActionResult payOrderNew(@RequestBody String body) throws Exception {
	      JSONObject object = JsonHelper.toJsonObject(body);
	      int sponsor = object.getIntValue("sponsor");
	      Long serialNum = object.getLong("serialNum");
	      long otherId = object.getLongValue("otherId");
	      int state = object.getIntValue("state");
	      Long wyChatId = object.getLong("wyChatId");
	      int avType = object.getIntValue("avType");
	      if(avType == 0){
				avType = 2;
			}
		  long userId = RequestUtils.getCurrent().getUserid();
		  return vChatVideoYXService.payOrderNew(userId, otherId, avType, sponsor, serialNum, state,wyChatId);
	}
	
	
	
	/**
	 * 主播或用户 主动退出
	 * @state  0 关 闭  1 开启
	 * @return
	 * @throws Exception
	 */
	@Login
	@UserClientService("vchatYX")
	@RequestMapping(value = "/userExit", method = RequestMethod.POST)
	public @ResponseBody ActionResult userExit(@RequestBody String body) throws Exception {
		JSONObject object = JsonHelper.toJsonObject(body);
		long otherId = object.getLongValue("otherId");
		long serialNum = object.getLongValue("serialNum");
		int avType = object.getIntValue("avType");
		long userId = RequestUtils.getCurrent().getUserid();
		
		return vChatVideoYXService.userExit(userId, otherId, serialNum,avType);
	}
	
	
	
	/**
	 * 摄像头状态
	 * @state  0 关 闭  1 开启
	 * @return
	 * @throws Exception
	 */
	@Login
	@UserClientService("vchatYX")
	@RequestMapping(value = "/cameraStatus", method = RequestMethod.POST)
	public @ResponseBody ActionResult cameraStatus(@RequestBody String body) throws Exception {
		JSONObject object = JsonHelper.toJsonObject(body);
		long otherId = object.getLongValue("otherId");
		int state = object.getIntValue("state");
		long userId = RequestUtils.getCurrent().getUserid();

		VChatVideoTCPDto dto = new VChatVideoTCPDto();
		dto.setType(VChatVideoTCPTypeEnum.video.getCode());
		dto.setSubType(VChatVideoStatusEnum.camera_status.getCode());
		dto.setData(state);
		neteaseAgent.pushOneAttachMessage(userId, vChatVideoYXService.getRealUserId(otherId), JsonHelper.toJson(dto));
		return ActionResult.success();
	}
	
	/**
	 * 视频聊拔打前检查
	 * @return
	 * @throws Exception
	 */
	@Login
	@UserClientService("vchatYX")
	@RequestMapping(value = "/dialingNewCheck", method = RequestMethod.POST)
	public @ResponseBody ActionResult dialingNewCheck(@RequestBody String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long toUserId = json.getLong("otherId");
		long type = json.getLongValue("type");
		int avType = json.getIntValue("avType");
		if(avType == 0){
			avType = 2;
		}
		return vChatVideoYXService.dialingNewCheck(userId, toUserId,type,avType);
		
	}
	
	
	/**
	 * 通话结束后主播评论列表
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/anchorEvaluationList", method = RequestMethod.POST)
	public @ResponseBody ActionResult anchorEvaluationList(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long serialNum = json.getLongValue("serialNum");
		return vChatVideoYXService.anchorEvaluationList(serialNum);
	}
	
	
	/**
	 * 通话结束后主播评论结果提交
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/anchorEvaluation", method = RequestMethod.POST)
	public @ResponseBody ActionResult anchorEvaluation(@RequestBody(required=false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long anchorId = json.getLong("anchorId");
		long serialNum = json.getLongValue("serialNum");
		String evaluationText = json.getString("evaluationText");
		JSONArray idArray = json.getJSONArray("evaluationIdList");
		List<Long> list = new ArrayList<Long>();
		idArray.forEach(v->{
			list.add(Long.valueOf(""+v));
		});
	
		return vChatVideoYXService.anchorEvaluation(userId,anchorId,serialNum,list,evaluationText);
	}
	
	/**
	 * 通话结束后主播对用户评论列表
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/annchorToUserEvaluation", method = RequestMethod.POST)
	public @ResponseBody ActionResult annchorToUserEvaluation(@RequestBody(required=false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long otherId = json.getLong("otherId");
		long serialNum = json.getLongValue("serialNum");
		
		JSONArray idArray = json.getJSONArray("type");
		
		List<Long> list = new ArrayList<Long>();
		idArray.forEach(v->{
			list.add(Long.valueOf(""+v));
		});
	
		return vChatVideoYXService.annchorToUserEvaluation(userId,otherId,serialNum,list );
	}
	
	
	/**
	 * 我的通话记录
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/myPhone", method = RequestMethod.POST)
	public @ResponseBody ActionResult myPhone(@RequestBody(required=false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		String stamp = json.getString("stamp");
		return vChatVideoYXService.getMyPhone(userId,stamp);
	}
	
	
	  /**
	   * 电话没接通通知，对方挂断或，超时自动挂断
	   */
	  @Login
	  @ResponseBody
	  @RequestMapping(value = "phone/fail", method = RequestMethod.POST)
	  public ActionResult dialingPhoneFail(@RequestBody String body) {
	    try {
	      BeatContext context = RequestUtils.getCurrent();
	      Long userId = context.getUserid();
	      JSONObject object = JsonHelper.toJsonObject(body);
	      Long otherId = object.getLongValue("otherId");
		  int avType = object.getIntValue("avType");
		  if(avType == 0){
				avType = 2;
		  }
	      return vChatVideoYXService.exitRoom(userId,otherId,avType);
	    } catch (Exception e) {
	      logger.error("communicationPhoneFail_ERROR", e);
	      return ActionResult.fail();
	    }
	  }
	
}

package com.tigerjoys.shark.miai.controller;

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
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.service.IVchatTurntableService;

/**
 * 互动转盘
 * @author yangjunming
 *
 */
@Controller
@RequestMapping(value = "/api/vchatYX/turntable", method=RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class VChatTurntableController {
	
	@Autowired
	private IVchatTurntableService vchatTurntableService;
	
	
	/**
	 * 转盘首页
	 * @return
	 * @throws Exception
	 */
	@Login
	@UserClientService("vchatYX")
	@RequestMapping(value = "/homeInfo", method=RequestMethod.POST)
	public @ResponseBody ActionResult homeInfo(@RequestBody String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long otherId = json.getLong("otherId");
		
		return vchatTurntableService.homeInfo(userId, otherId);
	}
	
	
	/**
	 * 转盘中奖信息
	 * @return
	 * @throws Exception
	 */
	@Login
	@UserClientService("vchatYX")
	@RequestMapping(value = "/turntableInfo", method=RequestMethod.POST)
	public @ResponseBody ActionResult turntableInfo(@RequestBody String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		Long otherId = json.getLong("otherId");
		
		return vchatTurntableService.turntableInfo(userId, otherId);
	}
	
	  /**
	   * 转盘扣费
	   */
	  @Login
	  @UserClientService("comm")
	  @ResponseBody
	  @RequestMapping(value = "/turntablePay", method = RequestMethod.POST)
	  public ActionResult turntablePay(@RequestBody String body) throws Exception {
	
	      JSONObject object = JsonHelper.toJsonObject(body);
	      String orderId = object.getString("orderId");   // 1视频 2 聊天  礼物发送
	      return vchatTurntableService.turntablePay(orderId);
	   
	  }
	
	
}

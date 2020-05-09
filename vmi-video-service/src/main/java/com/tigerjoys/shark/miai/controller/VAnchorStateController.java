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
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserForegroundAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;

/**
 * 处理手机切屏状态信息接口
 * @author shiming
 *
 */
@Controller
@RequestMapping(value="/api",produces=Produce.TEXT_ENCODE)
public class VAnchorStateController {
	
	/**
	 * 客户端在线状态信息
	 */
	private static final int APP_STATE_ONRESUME = 1;
    private static final int APP_STATE_ONPAUSE = 2;
    private static final int APP_STATE_ONDESTROY = 3;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserForegroundAgent userForegroundAgent;
	
	@Autowired
	private IUserOnlineListAgent userOnlineListAgent;

	@NoLogin
	@RequestMapping(value="/anchor/v/state",method=RequestMethod.POST)
	public @ResponseBody ActionResult getAnchorState(@RequestBody String body) throws Exception {
		//1 首先检测对应的请求是否携带对应的用户id值
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		if(Tools.isNotNull(header)) {
			long userid = header.getUserid();
			if(userid > 0) {
				//2获取对应的用户
				UserBO user = userAgent.findById(userid);
				if(Tools.isNotNull(user)) {
					JSONObject json = JsonHelper.toJsonObject(body);
					Integer state = json.getInteger("state");
					if(Tools.isNotNull(state)) {
						if(user.isWaiter()) {
							//根据传递的状态处理 来进行区分处理
							if(state.intValue() == APP_STATE_ONRESUME) {
								//用户切入前台了    将对应的用户放置到缓存中    即在前台展示的用户在缓存中
								userForegroundAgent.addForegroundAnchor(userid);
								userForegroundAgent.removeBackgroundAnchor(userid);
							} else if(state.intValue() == APP_STATE_ONPAUSE) {
								//用户切入后台了    将对应的用户清除缓存
								userForegroundAgent.removeForegroundAnchor(userid);
								userForegroundAgent.addBackgroundAnchor(userid);
							} else if(state.intValue() == APP_STATE_ONDESTROY) {
								//用户双击退出了
								userForegroundAgent.removeForegroundAnchor(userid);
								//userForegroundAgent.addBackgroundAnchor(userid);
							} 
						} else {
							//根据传递的状态处理 来进行区分处理
							if(state.intValue() == APP_STATE_ONRESUME) {
								//用户切入前台了    将对应的用户放置到缓存中    即在前台展示的用户在缓存中
								userForegroundAgent.addForegroundUser(userid);
							} else if(state.intValue() == APP_STATE_ONPAUSE) {
								//用户切入后台了    将对应的用户清除缓存
								userForegroundAgent.removeForegroundUser(userid);
							} else if(state.intValue() == APP_STATE_ONDESTROY) {
								//用户双击退出了
								userForegroundAgent.removeForegroundUser(userid);
								//将用户用户设置为离线
								userOnlineListAgent.removeOnlineUser(userid);
							} 
						}
					}
				}
			}
		}
		return ActionResult.success();
	}
}

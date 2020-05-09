package com.tigerjoys.shark.miai.netease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;

/** 
  * 向安卓推聊天消息
  * @author mouzhanpeng at [2017年11月29日 下午5:47:02] 
  * @since JDK 1.8.0 
  */
//@Service
public class PushAndroidObserver extends Observer {

	@Autowired
	private INewPushAgent newPushAgent;
	
	@Autowired
	private IUserOnlineListAgent userOnlineListAgent;
	
	@Override
	public void deal(UserBO fromUser, UserBO toUser, JSONObject json) throws Exception {
		if (1 == toUser.getPlatform()) {// 安卓  
			if (!userOnlineListAgent.existsOnline(toUser.getUserid())) {// 不在线时推送
				String msgType = json.getString("msgType");
				PushParamDto param = PushHelper.getPushParamDto(toUser, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.chat);
				param.setTitle(fromUser.getNickname());
				if ("TEXT".equals(msgType)) {
					param.setPackageName(toUser.getPackageName());
					param.setContent(json.getString("body"));
					param.setUserId(fromUser.getUserid());
					param.setUserHead(Tools.isNull(fromUser.getPhoto()) ? Const.getDefaultUserFace() : Const.getCdn(fromUser.getPhoto()));
					param.setUserName(fromUser.getNickname());
					newPushAgent.pushMessageToSingleUser(param);
				} else if ("PICTURE".equals(msgType)) {
					param.setPackageName(toUser.getPackageName());
					param.setContent("*[图片]*");
					param.setUserId(fromUser.getUserid());
					param.setUserHead(Tools.isNull(fromUser.getPhoto()) ? Const.getDefaultUserFace() : Const.getCdn(fromUser.getPhoto()));
					param.setUserName(fromUser.getNickname());
					newPushAgent.pushMessageToSingleUser(param);
				}
			}
		}
	}
}

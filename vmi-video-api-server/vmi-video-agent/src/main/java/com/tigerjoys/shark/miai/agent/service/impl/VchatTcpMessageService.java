package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.dto.VChatVideoTCPDto;
import com.tigerjoys.shark.miai.agent.enums.VChatPopupStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoTCPTypeEnum;
import com.tigerjoys.shark.miai.agent.service.IVchatTcpMessageService;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;

@Service
public class VchatTcpMessageService implements IVchatTcpMessageService {
	private final Logger logger = LoggerFactory.getLogger(getClass());	

	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	@Override
	public ActionResult checkPorn(long userid,long orderId, int type, String hitInfo) throws Exception {
		VChatVideoTCPDto dto = new VChatVideoTCPDto();
		dto.setType(VChatVideoTCPTypeEnum.video.getCode());
		dto.setSubType(VChatVideoStatusEnum.check_Porn.getCode());
		
		Map<String,Object> outMap = new HashMap<String,Object>();
		outMap.put("hitInfo",hitInfo );
		outMap.put("action",type>0 ?1:0 );
		
		if(orderId>0 && type>0 ){
			vchatRoomContract.checkPorn(orderId, userid, type);
		}
		dto.setData(JsonHelper.toJson(outMap));
		neteaseAgent.pushOneAttachMessage(userid,userid, JsonHelper.toJson(dto)); // 主播用户可能是假账户
		return ActionResult.success();
	}
	
	@Override
	public ActionResult sendShortVideoOpenStatus(long userid, int status) throws Exception {
		try{
		VChatVideoTCPDto dto = new VChatVideoTCPDto();
		dto.setType(VChatVideoTCPTypeEnum.popup.getCode());
		dto.setSubType(VChatPopupStatusEnum.short_video_open.getCode());
		dto.setData(status);
		neteaseAgent.pushOneAttachMessage(userid,userid, JsonHelper.toJson(dto)); 
		}catch (Exception e) {
			logger.info("userid:"+userid+"发送失败:",e);
		}
		return ActionResult.success();
		
	}

}

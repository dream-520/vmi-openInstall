package com.tigerjoys.shark.miai.netease;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.contract.IAppMsgDetailContract;
import com.tigerjoys.shark.miai.inter.entity.AppMsgDetailEntity;

/** 
  * 完成消息存储到数据库处理
  * @author shiming at [2018年07月05日 上午17:20:03] 
  * @since JDK 1.8.0 
  */
@Service
public class SendMsgToDBObserver extends Observer {

	@Autowired
	private IAppMsgDetailContract appMsgDetailContract;
	
	/**
		{
			"fromNick": "小爱同学",
			"msgType": "TEXT",
			"msgidServer": "30116145957",
			"fromAccount": "71294738136891648",
			"fromClientType": "AOS",
			"fromDeviceId": "5f551eb0-8f32-4e42-b007 - a7b7e4f45d44 ",
			"eventType ":"1 ",
			"body ":"嗨 ",
			"convType ":"PERSON ",
			"msgidClient ":"41 a4db1f4412405086738530df2304e0 ",
			"resendFlag ":"0 ",
			"msgTimestamp ":"1530644263912 ",
			"to ":"65418719477628672 "
    	}
	 */
	@Override
	public void deal(UserBO from, UserBO to, JSONObject json) throws Exception {
		if(Tools.isNotNull(from) && Tools.isNotNull(to)) {
			//解析获取需要的消息内容
			String msgType = json.getString("msgType");
			String body = json.getString("body");
			String convType = json.getString("convType");
			long msgTimestamp = json.getLongValue("msgTimestamp");
			JSONObject attach = json.getJSONObject("attach");
			
			AppMsgDetailEntity msgDetail = new AppMsgDetailEntity();
			int tag = 0;
			if(msgType.equals("TEXT")) {
				msgDetail.setBody(body);
				tag = 1;
			} else if(msgType.equals("PICTURE")) {
				msgDetail.setAttach(attach.toJSONString());
				tag = 2;
			} else if(msgType.equals("CUSTOM")) {
				//处理新增的自定义消息
				msgDetail.setAttach(attach.toJSONString());
				tag = 3;
			}
			msgDetail.setConvType(convType);
			msgDetail.setMsgTimestamp(Tools.getDateTime(Tools.getDateTime(msgTimestamp)));
			msgDetail.setMsgType(msgType);
			msgDetail.setFromid(from.getUserid());
			msgDetail.setToid(to.getUserid());
			msgDetail.setTag(tag);
			msgDetail.setCreate_time(new Date());
			appMsgDetailContract.insert(msgDetail);
		}
	}
	
}

/**
 * 
 */
package com.tigerjoys.shark.miai.agent.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.shark.miai.common.util.ShieldWordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.IOUtils;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.constant.NeteaseApiConst;
import com.tigerjoys.shark.miai.agent.enums.NeteaseErrorEnum;
import com.tigerjoys.shark.miai.agent.utils.CheckSumBuilder;

/**
 * ClassName: NeteaseAgent <br/>
 * date: 2017年5月11日 上午11:27:21 <br/>
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
@Service
public class NeteaseAgentImpl implements INeteaseAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// 业务处理线程池
	private final Executor executor = Executors.newWorkStealingPool();

	public JSONObject acquireData(String api, Map<String, String> params) throws Exception {
		String curTime = String.valueOf(System.currentTimeMillis() / 1000L);
		// 设置请求的header
		Map<String, String> header = new HashMap<>();
		header.put("AppKey", NeteaseApiConst.APP_KEY);
		header.put("Nonce", NeteaseApiConst.NONCE);
		header.put("CurTime", curTime);
		header.put("CheckSum", CheckSumBuilder.getCheckSum(NeteaseApiConst.APP_SECRET, NeteaseApiConst.NONCE, curTime));
		logger.info("wyInfo:url="+api+";params:="+JsonHelper.toJson(params));
		// 执行请求
		ResponseStatus response = null;
		try{
			response = HttpUtils.post(api, params, ECharset.UTF_8, EContentType.APPLICATION_FORM_URLENCODED, header);
		}catch(Exception e){
			logger.info("wyInfo:url="+api+";params:="+JsonHelper.toJson(params),e);
			HashMap<String, Integer> hsmp = new HashMap<>();
			hsmp.put("code", 404);
			return JsonHelper.toJsonObject(JsonHelper.toJson(hsmp));
		}
		// 打印执行结果
		String json = response.getContent();
		logger.info("wyInfo:url="+api+";params:="+JsonHelper.toJson(params)+";result:"+json);
		return JsonHelper.toJsonObject(json);
	}

	@Override
	public JSONObject createUser(Map<String, String> params) throws Exception {
		return acquireData(NeteaseApiConst.USER_CREATE, params);
	}

	@Override
	public JSONObject updateUser(Map<String, String> params) throws Exception {
		return acquireData(NeteaseApiConst.USER_UPDATE, params);
	}

	@Override
	public JSONObject queryHistory(Map<String, String> params) throws Exception {
		return acquireData(NeteaseApiConst.HISTORY_QUERY, params);
	}

	@Override
	public JSONObject sendMessage(Map<String, String> params) throws Exception {
		return acquireData(NeteaseApiConst.MSG_SEND, params);
	}
	
	@Override
	public JSONObject sendAttachMessage(Map<String, String> params) throws Exception {
		return acquireData(NeteaseApiConst.SEND_ATTACH_MSG, params);
	}

	@Override
	public JSONObject badRelation(Map<String, String> params) throws Exception {
		return acquireData(NeteaseApiConst.BAD_RELATION, params);
	}

	@Override
	public JSONObject badRelationList(Map<String, String> params) throws Exception {
		return acquireData(NeteaseApiConst.LIST_BAD_RELATION, params);
	}

	@Override
	public void pushOneMessage(long fromId, long toId, String message, boolean isSync) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("from", String.valueOf(fromId));
		params.put("ope", "0");
		params.put("to", String.valueOf(toId));
		params.put("type", "0");
		Map<String, String> msg = new HashMap<>();
		msg.put("msg", message);
		params.put("body", JsonHelper.toJson(msg));
		Map<String, Object> sync = new HashMap<>();
		sync.put("sendersync", isSync);
		params.put("option", JsonHelper.toJson(sync));
		JSONObject data = sendMessage(params);
		if (NeteaseErrorEnum.success.getCode() != data.getIntValue("code")) {
			logger.warn("sending push msg occured error:{}", data);
		}
	}
	
	@Override
	public void pushOneMessageNORoam(long fromId, long toId, String message, boolean isSync) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("from", String.valueOf(fromId));
		params.put("ope", "0");
		params.put("to", String.valueOf(toId));
		params.put("type", "0");
		Map<String, String> msg = new HashMap<>();
		msg.put("msg", message);
		params.put("body", JsonHelper.toJson(msg));
		Map<String, Object> sync = new HashMap<>();
		sync.put("sendersync", isSync);
		sync.put("roam", false);
		params.put("option", JsonHelper.toJson(sync));
		JSONObject data = sendMessage(params);
		if (NeteaseErrorEnum.success.getCode() != data.getIntValue("code")) {
			logger.warn("sending push msg occured error:{}", data);
		}
	}
	
	
	@Override
	public void pushPicMessage(long fromId, long toId, String picUrl,int width,int height,int size,String ext,String md5, boolean isSync) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("from", String.valueOf(fromId));
		params.put("ope", "0");
		params.put("to", String.valueOf(toId));
		params.put("type", "1");
		Map<String, Object> msg = new HashMap<>();
		msg.put("name", md5);
		msg.put("md5", md5);
		msg.put("url", picUrl);
		msg.put("ext", ext);
		msg.put("w", width);
		msg.put("h", height);
		msg.put("size", size);
		params.put("body", JsonHelper.toJson(msg));
		Map<String, Object> sync = new HashMap<>();
		sync.put("sendersync", isSync);
		params.put("option", JsonHelper.toJson(sync));
		JSONObject data = sendMessage(params);
		if (NeteaseErrorEnum.success.getCode() != data.getIntValue("code")) {
			logger.warn("sending push msg occured error:{}", data);
		}
	}
	
	
	
	@Override
	public void pushOneAttachMessage(long fromId, long toId, String message) throws Exception{
		pushOneAttachMessage(fromId,toId,message ,"");
	}
	
	@Override
	public void pushOneAttachMessage(long fromId, long toId, String message ,String title) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("from", "adminVmi");
		params.put("msgtype", "0");
		params.put("to", String.valueOf(toId));
		params.put("attach", message);
		params.put("pushcontent", title);
		params.put("payload", message);
		params.put("sound", "");
		params.put("save", "1");  //1表示只发在线，2表示会存离线，其他会报414错误。默认会存离线
		Map<String, Object> sync = new HashMap<>();
		params.put("option", JsonHelper.toJson(sync));
		JSONObject data = sendAttachMessage(params);
		if (NeteaseErrorEnum.success.getCode() != data.getIntValue("code")) {
			logger.warn("sending push msg occured error:{}", data);
		}else{
			logger.warn("pushOneAttachMessage_message:fromId={},toId={},msg={}",fromId,toId, message);
		}
	}
	
	
	

	@Override
	public void sendGiftPic(long fromId, long toId, String memo, String picUrl, long giftId, boolean isSync) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("from", String.valueOf(fromId));
		params.put("ope", "0");
		params.put("to", String.valueOf(toId));
		params.put("type", "100");

		Map<String, Object> data = new HashMap<>();
		data.put("giftType", giftId);
		data.put("giftName", memo);
		data.put("giftUrl", Const.getCdn(picUrl));
		Map<String, Object> msg = new HashMap<>();
		msg.put("type", 1);
		msg.put("data", data);
		params.put("body", JsonHelper.toJson(msg));
		Map<String, Object> sync = new HashMap<>();
		sync.put("sendersync", isSync);
		params.put("option", JsonHelper.toJson(sync));
		JSONObject res = sendMessage(params);
		if (NeteaseErrorEnum.success.getCode() != res.getIntValue("code")) {
			logger.warn("sending gift-pic occured error:{}", data);
		}
	}

	
	@Override
	public void sendAudioMsg(long fromId, long toId, long audioId, String audioUrl,long audioTime , boolean isSync) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("from", String.valueOf(fromId));
		params.put("ope", "0");
		params.put("to", String.valueOf(toId));
		params.put("type", "100");

		Map<String, Object> data = new HashMap<>();
		data.put("voiceSerial", audioId+"");
		data.put("voiceUrl", Const.getVchatAudio(audioUrl));
		data.put("voiceTime",audioTime);
		Map<String, Object> msg = new HashMap<>();
		msg.put("type", 3);
		msg.put("data", data);
		params.put("body", JsonHelper.toJson(msg));
		Map<String, Object> sync = new HashMap<>();
		sync.put("sendersync", isSync);
		params.put("option", JsonHelper.toJson(sync));
		JSONObject res = sendMessage(params);
		if (NeteaseErrorEnum.success.getCode() != res.getIntValue("code")) {
			logger.warn("sending audioMsg occured error:{}", data);
		}
	}
	
	
	
	@Override
	public void modifyBadRelation(long userId, long blackId, boolean adding) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("accid", String.valueOf(userId));
		params.put("targetAcc", String.valueOf(blackId));
		params.put("relationType", "1");
		params.put("value", adding ? "1" : "0");
		JSONObject data = badRelation(params);
		if (NeteaseErrorEnum.success.getCode() != data.getIntValue("code")) {
			logger.warn("adding black list occured error:{}", data);
		}
	}

	@Override
	public Map<String, Integer> attachNeteaseMsg(HttpServletRequest request, AttachMessageCallback callback) {
		/*
		Map<String, Integer> result = new HashMap<>();
		try {
			String requestBody = IOUtils.read(request.getReader());
			if (Tools.isNull(requestBody)) {
				logger.warn("request wrong, empty body!");
				result.put("code", NeteaseErrorEnum.param_error.getCode());
				return result;
			}
			// 获取部分request header，并打印
			String contentType = request.getContentType();
			String appKey = request.getHeader("AppKey");
			String curTime = request.getHeader("CurTime");
			String md5 = request.getHeader("MD5");
			String checkSum = request.getHeader("CheckSum");
			logger.debug("request headers: ContentType={}, AppKey={}, CurTime={}, MD5={}, CheckSum={}", contentType, appKey, curTime, md5, checkSum);
			logger.debug("request body: {}", requestBody);
			// 获取计算过的md5及checkSum
			String verifyMd5 = CheckSumBuilder.getMD5(requestBody);
			String verifyCheckSum = CheckSumBuilder.getCheckSum(NeteaseApiConst.APP_SECRET, verifyMd5, curTime);
			logger.debug("verifyMD5={}, verifyChecksum={}", verifyMd5, verifyCheckSum);
			// 比较md5、checkSum是否一致
			if (!verifyCheckSum.equals(checkSum)) {
				logger.warn("request checkSum wrong!");
				result.put("code", NeteaseErrorEnum.param_error.getCode());
				return result;
			}
			// 避免过长等待，导致超时
			executor.execute(() -> callback.dealMessage(JsonHelper.toJsonObject(requestBody)));
			result.put("code", NeteaseErrorEnum.success.getCode());
			return result;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			result.put("code", NeteaseErrorEnum.param_error.getCode());
			return result;
		}
		*/
		

		Map<String, Integer> result = new HashMap<>();
		String data =  request.getParameter("data");
		try {
			if(Tools.isNotNull(data) && data.length() > 0) {
	        	// 避免过长等待，导致超时
				logger.debug("request body: {}", data);
				executor.execute(() -> callback.dealMessage(JsonHelper.toJsonObject(data)));
				result.put("code", NeteaseErrorEnum.success.getCode());
	        } else {
	        	logger.warn("request wrong, empty data!");
	        	result.put("code", NeteaseErrorEnum.param_error.getCode());
			}
			return result;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			result.put("code", NeteaseErrorEnum.param_error.getCode());
			return result;
		}
		
		/*
		//测试数据
		//String data = "{\"duration\":\"342\",\"ext\":\"extra_data\",\"createtime\":\"1561101714754\",\"members\":\"[{\\\"duration\\\":171,\\\"accid\\\":\\\"89136902629818624\\\",\\\"userType\\\":1},{\\\"duration\\\":171,\\\"caller\\\":true,\\\"accid\\\":\\\"135167028410515712\\\",\\\"userType\\\":1}]\",\"eventType\":\"5\",\"type\":\"VEDIO\",\"channelId\":\"51154180966357\",\"live\":\"0\",\"status\":\"SUCCESS\"}";
		String data = "{\"fileinfo\":\"[{\\\"vid\\\":\\\"2557254655\\\",\\\"filename\\\":\\\"0-51154180966357-mix.mp4\\\",\\\"pieceindex\\\":\\\"0\\\",\\\"size\\\":\\\"23919253\\\",\\\"type\\\":\\\"mp4\\\",\\\"user\\\":\\\"0\\\",\\\"mix\\\":true,\\\"url\\\":\\\"http://jdvodr6xgogfm.vod.126.net/jdvodr6xgogfm/0-51154180966357-0-mix.mp4\\\",\\\"channelid\\\":\\\"51154180966357\\\",\\\"timestamp\\\":\\\"1561101888\\\",\\\"md5\\\":\\\"2f73d4a9bb6c910349d20a26a04c875c\\\"}]\",\"eventType\":\"6\"}";
		executor.execute(() -> callback.dealMessage(JsonHelper.toJsonObject(data)));
		return null;
		*/
	}

	
	
	@Override
	public Map<String, Integer> receiveMsgNeteaseMsg(HttpServletRequest request, AttachMessageCallback callback) {
		Map<String, Integer> result = new HashMap<>();
		try {
			String requestBody = IOUtils.read(request.getReader());
			if (Tools.isNull(requestBody)) {
				logger.warn("neteaseReciveMsg:request wrong, empty body!");
				result.put("errCode", NeteaseErrorEnum.notify_safe_msg_success.getCode());
				return result;
			}
			// 获取部分request header，并打印
			String contentType = request.getContentType();
			String appKey = request.getHeader("AppKey");
			String curTime = request.getHeader("CurTime");
			String md5 = request.getHeader("MD5");
			String checkSum = request.getHeader("CheckSum");
			logger.debug("neteaseReciveMsg:request headers: ContentType={}, AppKey={}, CurTime={}, MD5={}, CheckSum={}", contentType, appKey, curTime, md5, checkSum);
			logger.debug("neteaseReciveMsg:request body: {}", requestBody);
			// 获取计算过的md5及checkSum
			String verifyMd5 = CheckSumBuilder.getMD5(requestBody);
			String verifyCheckSum = CheckSumBuilder.getCheckSum(NeteaseApiConst.APP_SECRET, verifyMd5, curTime);
			logger.debug("neteaseReciveMsg:verifyMD5={}, verifyChecksum={}", verifyMd5, verifyCheckSum);
			// 比较md5、checkSum是否一致
			if (!verifyCheckSum.equals(checkSum)) {
				logger.warn("neteaseReciveMsg:request checkSum wrong!");
				result.put("errCode", NeteaseErrorEnum.notify_safe_msg_success.getCode());
				return result;
			}
			JSONObject json =  JsonHelper.toJsonObject(requestBody);			
			// 避免过长等待，导致超时
		    //	executor.execute(() -> callback.dealMessage(json));
			
			String body = json.getString("body");
			/*
			if(Tools.isNotNull(body)){
				String newBody = body.replaceAll("[a-zA-Z0-9]", "");
				if(newBody.length()<body.length()){
					result.put("errCode", NeteaseErrorEnum.notify_safe_msg_err.getCode());
					return result;
				}
			}
			*/
			// 敏感词监测
			Set<String> shieldWordSet = ShieldWordUtils.getSensitiveWord(body, ShieldWordUtils.MIN_MATCH_TYPE);
			if (Tools.isNotNull(shieldWordSet)) {
				logger.info("neteaseReciveMsg:你输入的内容包含敏感词"+JsonHelper.toJson(shieldWordSet));
				result.put("errCode", NeteaseErrorEnum.notify_safe_msg_err.getCode());
			}else {
				result.put("errCode", NeteaseErrorEnum.notify_safe_msg_success.getCode());
			}
			
			return result;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			result.put("errCode", NeteaseErrorEnum.notify_safe_msg_success.getCode());
			return result;
		}
	}

	
	
	
	public void testCreateUser() throws Exception{
		Map<String, String> params = new HashMap<>();
		params.put("accid", "adminVmi");
		params.put("name", "adminVmiName");
		params.put("icon",
				"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506578844&di=6ce7ae3acce7dd07b15c07b1075c0958&imgtype=jpg&er=1&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201406%2F29%2F20140629180728_ktRJ2.jpeg");
		params.put("token", "adminVmiToken");
		JSONObject json = createUser(params);
		System.out.println(json.toString());
	}
	
}

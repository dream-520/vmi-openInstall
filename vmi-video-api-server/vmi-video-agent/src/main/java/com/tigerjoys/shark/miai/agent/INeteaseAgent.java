/**
 * 
 */
package com.tigerjoys.shark.miai.agent;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName: INeteaseAgent <br/>
 * date: 2017年5月11日 上午11:20:57 <br/>
 * 
 * 接口参数参照网易文档
 * 
 * @see http://dev.netease.im/docs/product/IM%E5%8D%B3%E6%97%B6%E9%80%9A%E8%AE%AF/%E6%9C%8D%E5%8A%A1%E7%AB%AFAPI%E6%96%87%E6%A1%A3
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public interface INeteaseAgent {

	/**
	 * 网易云信接口调用
	 * 
	 * @param api
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public JSONObject acquireData(String api, Map<String, String> params) throws Exception;
	
	/**
	 * 用户注册
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject createUser(Map<String, String> params) throws Exception;

	/**
	 * 用户信息更新
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject updateUser(Map<String, String> params) throws Exception;

	/**
	 * 获取聊天历史
	 * 
	 * @param params
	 * @return
	 */
	public JSONObject queryHistory(Map<String, String> params) throws Exception;

	/**
	 * 发送消息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public JSONObject sendMessage(Map<String, String> params) throws Exception;

	/**
	 * 发送自定义系统消息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public JSONObject sendAttachMessage(Map<String, String> params) throws Exception;
	
	
	
	
	/**
	 * 加黑名单
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public JSONObject badRelation(Map<String, String> params) throws Exception;

	/**
	 * 查询黑名单
	 *
	 * @param params
	 * @throws Exception
	 */
	public JSONObject badRelationList(Map<String, String> params) throws Exception;

	/**
	 * IM消息推送
	 * 
	 * @param fromId - 己方ID
	 * @param toId - 对方ID
	 * @param message - 消息内容
	 * @param isSync - 是否同步到发送者
	 * @throws Exception
	 */
	public void pushOneMessage(long fromId, long toId, String message, boolean isSync) throws Exception;
	
	/**
	 * 不待漫游功能的发送消息
	 * @param fromId
	 * @param toId
	 * @param message
	 * @param isSync
	 * @throws Exception
	 */
	public void pushOneMessageNORoam(long fromId, long toId, String message, boolean isSync) throws Exception;

	/**
	 * IM自定义消息推送,管理帐号发送消息
	 * 
	 * @param fromId - 己方ID
	 * @param toId - 对方ID
	 * @param message - 消息内容
	 * @throws Exception
	 */
	public void pushOneAttachMessage(long fromId, long toId, String message) throws Exception;
	
	/**
	 * IM自定义消息推送,管理帐号发送消息
	 * 
	 * @param fromId - 己方ID
	 * @param toId - 对方ID
	 * @param message - 消息内容
	 * @param title - IOS标题
	 * @throws Exception
	 */
	public void pushOneAttachMessage(long fromId, long toId, String message,String title) throws Exception;
	
	
	/**
	 * IM消息推送
	 *
	 * @param fromId - 己方ID
	 * @param toId - 对方ID
	 * @param memo - 消息内容
	 * @param giftId
	 * @param picUrl
	 * @param isSync - 是否同步到发送者
	 * @throws Exception
	 */
	public void sendGiftPic(long fromId, long toId, String memo, String picUrl, long giftId, boolean isSync) throws Exception;

	/**
	 * 
	 * @param fromId	-	己方ID
	 * @param toId		-	对方ID
	 * @param audioId	-	语音ID
	 * @param audioUrl	-	语音地址
	 * @param audioTime	-	时长
	 * @param isSync 	-	是否同步到发送者
	 * @throws Exception
	 */
	public void sendAudioMsg(long fromId, long toId, long audioId, String audioUrl,long audioTime , boolean isSync) throws Exception;
	
	/**
	 * 发送图片
	 * @param fromId
	 * @param toId
	 * @param picUrl   地址
	 * @param width		图片宽
	 * @param height	图片高
	 * @param size		图片大小
	 * @param md5		图片MD5
	 * @param isSync
	 * @throws Exception
	 */
	public void pushPicMessage(long fromId, long toId, String picUrl,int width,int height,int size,String ext,String md5, boolean isSync) throws Exception;
	
	/**
	 * 设置黑名单
	 * 
	 * @param userId - 用户ID
	 * @param blackId - 黑名单用户ID
	 * @param adding - 是否加黑名单，否为取消黑名单
	 * @throws Exception
	 */
	public void modifyBadRelation(long userId, long blackId, boolean adding) throws Exception;

	/**
	 * IM消息抄送
	 * 
	 * @param request
	 * @param callback
	 * @return
	 */
	public Map<String, Integer> attachNeteaseMsg(HttpServletRequest request, AttachMessageCallback callback);
	
	/**
	 * IM反垃圾处理
	 * @param request
	 * @param callback
	 * @return
	 */
	public Map<String, Integer> receiveMsgNeteaseMsg(HttpServletRequest request, AttachMessageCallback callback);
	/**
	  * 抄送消息回调接口
	  * @author mouzhanpeng at [2017年9月22日 上午11:12:05] 
	  * @since JDK 1.8.0
	 */
	public static interface AttachMessageCallback{
		void dealMessage(JSONObject json);
	}
}

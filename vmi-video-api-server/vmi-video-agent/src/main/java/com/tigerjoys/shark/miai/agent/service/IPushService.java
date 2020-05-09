package com.tigerjoys.shark.miai.agent.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

/**
 * 推送接口调用
 * @author liuman
 *
 */
public interface IPushService {
	
	/**
	 * 发送推送数据，默认离线有效期限为24小时
	 * @param clientId - 客户端ID
	 * @param msg - 内容
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @param platformType 手机平台类型
	 * @return int
	 */
	public int sendMessage(String clientId, String msg,String msgType,int platformType);

	
	/**
	 * 发送推送数据
	 * @param clientId - 客户端ID
	 * @param msg - 内容
	 * @param sec - 离线有效时间，单位为毫秒。如果此值>0，则默认支持离线消息
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @param platformType 手机平台类型 
	 * @return int
	 */
	public int sendMessage(String clientId, String msg , long sec,String msgType,int platformType);

	
	/**
	 * 发送推送数据，默认离线有效期限为24小时
	 * @param clientId - 客户端ID
	 * @param msg - 内容，会自动转换成json
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @param platformType 手机平台类型
	 * @return int
	 */
	public int sendMessage(String clientId, Object msg,String msgType,int platformType);
	
	/**
	 * 发送推送数据
	 * @param clientId - 客户端ID
	 * @param msg - 内容，会自动转换成json
	 * @param sec - 离线有效时间，单位为毫秒。如果此值>0，则默认支持离线消息
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @param platformType 手机平台类型
	 * @return int
	 */
	public int sendMessage(String clientId, Object msg , long sec,String msgType,int platformType);

	
	/**
	 * 群推消息，如果传递了taskidAlias，则可以在个推的后台看本地群退的效果
	 * @param msg - 内容
	 * @param taskidAlias - 任务别名，可选，没有则传递空
	 * @param platformType - 手机平台，1安卓，2IOS，0不区分平台
	 * @param sec - 离线有效时间，单位为毫秒。如果此值>0，则默认支持离线消息
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @return boolean
	 */
	public boolean pushMessageToApp(Object msg , String taskidAlias , int platformType , long sec,String msgType);

	
	/**
	 * 群推消息，如果传递了taskidAlias，则可以在个推的后台看本地群退的效果
	 * @param msg - 内容
	 * @param taskidAlias - 任务别名，可选，没有则传递空
	 * @param platformType - 手机平台，1安卓，2IOS，0不区分平台
	 * @param sec - 离线有效时间，单位为毫秒。如果此值>0，则默认支持离线消息
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @return boolean
	 */
	public boolean pushMessageToApp(String msg , String taskidAlias , int platformType , long sec,String msgType);
	
	/**
	 * 批量推送消息
	 * @param aliasList 别名(clientId)设备集合
	 * @param msg 需要推送的消息内容体
	 * @param sec 推送消息存活时间
	 * @param msgType 消息类型(0:通知栏)
	 * @param platformType 手机平台类型
	 * @throws IOException
	 * @throws ParseException
	 */
	public void sendMessageToAliases(List<String> aliasList,String msg,long sec, String msgType,int platformType) throws IOException, ParseException; 

}

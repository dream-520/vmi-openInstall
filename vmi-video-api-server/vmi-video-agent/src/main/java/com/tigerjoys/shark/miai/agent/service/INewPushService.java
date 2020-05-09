package com.tigerjoys.shark.miai.agent.service;

import java.util.List;

/**
 * 推送接口调用
 * @author liuman
 *
 */
public interface INewPushService {
	
	
	/**
	 * 单个手机发送推送数据
	 * @param clientId - 客户端ID
	 * @param msg - 内容
	 * @param sec - 离线有效时间，单位为毫秒。如果此值>0，则默认支持离线消息
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @param notifyid 安卓端可以推送多条通知消息;ios端暂时用不到
	 * @return int
	 */
	public int sendMessage(String clientId, String msg , long sec,String msgType,int notifyid);


	/**
	 * 群推消息
	 * @param msg - 内容
	 * @param sec - 离线有效时间，单位为毫秒。如果此值>0，则默认支持离线消息
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @param notifyid 安卓端可以推送多条通知消息;ios端暂时用不到
	 * @return boolean
	 */
	public boolean groupPushMessageToApp(String msg , long sec,String msgType,int notifyid);
	
	/**
	 * 根据一批用户推消息
	 * @param clients - 客户id集合
	 * @param msg - 内容
	 * @param sec - 离线有效时间，单位为毫秒。如果此值>0，则默认支持离线消息
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @param notifyid 安卓端可以推送多条通知消息;ios端暂时用不到
	 * @return boolean
	 */
//	public boolean groupPushMessageByAliases(List<String> clients, String msg,long sec,String msgType,int notifyid);
	
	/**
	 * 根据用户别名订阅某个业务标签
	 * @param topic 业务主体
	 * @param topic aliases别名集合
	 */
	public void subscribeTopicByAlias(String topic,List<String> aliases) throws Exception;
	
	/**
	 * 根据主题信息将订阅该主题的用户推送消息
	 * @param topic - 主题名称
	 * @param msg - 内容
	 * @param sec - 离线有效时间，单位为毫秒。如果此值>0，则默认支持离线消息
	 * @param msgType 消息类型 0 为通知和透传，1  透传，2 通知 
	 * @param notifyid 安卓端可以推送多条通知消息;ios端暂时用不到
	 * @return boolean
	 */
	public boolean pushMessageToAppByTopic(String topic,String msg , long sec,String msgType,int notifyid);
	
	/**
	 * 根据用户别名取消订阅某个业务标签
	 * @param topic 业务主体
	 * @param topic aliases别名集合
	 */
	public void unsubscribeTopicByAlias(String topic,List<String> aliases) throws Exception;
}

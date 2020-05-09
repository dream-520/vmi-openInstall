package com.tigerjoys.shark.miai.agent;

import java.util.List;

import com.tigerjoys.shark.miai.agent.dto.PushParamDto;

/**
 * yoyo2.0业务推送通知服务代理接口(新)
 * @author 刘满
 *
 */
public interface INewPushAgent {
	
	/**
	 * 向单个用户推送通知
	 * @param PushParamDto 请求参数实体
	 */
	public void pushMessageToSingleUser(PushParamDto param) throws Exception;
	
	/**
	 * 向所有用户推送通知(如系统消息)
	 * @param PushParamDto 请求参数实体
	 */
	public void pushMessageToAllUser(PushParamDto param) throws Exception;
	
	/**
	 * 根据用户别名订阅某个业务标签
	 * @param platform 手机平台
	 * @param topic 业务主体
	 * @param topic aliases别名集合
	 */
	public void subscribeTopicByAlias(int platform,String topic,List<String> aliases) throws Exception;
	
	/**
	 * 根据主题想主题下的android用户发送推送通知
	 * @param PushParamDto 请求参数实体
	 * @param topic 主题名称
	 */
	public void pushMessageToAndroidUserByTopic(PushParamDto param,String topic) throws Exception;
	
	/**
	 * 根据主题想主题下的ios用户发送推送通知
	 * @param PushParamDto 请求参数实体
	 * @param topic 主题名称
	 */
	public void pushMessageToIosUserByTopic(PushParamDto param,String topic) throws Exception;
	
	/**
	 * 根据用户别名和主题取消订阅某个业务标签
	 * @param platform 手机平台
	 * @param topic 业务主体
	 * @param topic aliases别名集合
	 */
	public void unsubscribeTopicByAlias(int platform,String topic,List<String> aliases) throws Exception;
	
}

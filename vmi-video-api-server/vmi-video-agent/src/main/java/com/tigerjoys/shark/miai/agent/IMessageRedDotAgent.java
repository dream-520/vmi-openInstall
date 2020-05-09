package com.tigerjoys.shark.miai.agent;

import org.shark.miai.common.enums.BussinessMessageTypeEnum;

import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;

/**
 * 业务消息小红点代理接口
 * @author liuman
 *
 */
public interface IMessageRedDotAgent {
	
	/**
	 * 添加小红点消息
	 * @param key
	 * @param userId
	 */
	public void addRedDot(String key,long userId);
	
	/**
	 * 减少小红点消息
	 * @param key
	 * @param userId
	 */
	public void reduceRedDot(String key,long userId);
	
	/**
	 * 落地业务消息到数据库并将业务消息数量redis缓存+1
	 * @param userId
	 * @param bussinessMessageTypeEnum
	 * @param param 页面跳转参数 (类似于{"strValue1":"129884634"}这种格式)
	 * @throws Exception
	 */
	public void addBussinessMessage(long userId,BussinessMessageTypeEnum bussinessMessageTypeEnum,String param) throws Exception;
	
	/**
	 * 根据后台系统配置的发送系统消息
	 * @param mess
	 * @throws Exception
	 */
	public void sendSystemMessage(MessageTemplateEntity mess) throws Exception;
	
	/**
	 * 减少小红点消息
	 * @param key
	 * @param userId
	 */
	public void setZeroRedDot(String key,long userId);
}

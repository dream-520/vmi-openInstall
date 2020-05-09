package com.tigerjoys.shark.miai.test;

import java.util.Date;

import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;

/**
 * 系统消息相关的对象创建工厂
 * @author liuman
 *
 */
public class MessageFactory {
	
	/**
	 * 创建一个系统消息数据库对象
	 * @param userid - 用户ID
	 * @param title - 标题
	 * @param content - 内容
	 * @return MessageTemplateEntity
	 */
	/**
	 * @param userid
	 * @param title
	 * @param price
	 * @return
	 */
	public static MessageTemplateEntity createMessageTemplateEntity(long userid , String title , String content) {
		Date currDate = new Date();
		
		MessageTemplateEntity messageTemplateEntity = new MessageTemplateEntity();
		messageTemplateEntity.setContent(content);
		messageTemplateEntity.setContent_type(0);
		messageTemplateEntity.setCreate_adminid(11L);
		messageTemplateEntity.setCreate_time(currDate);
		messageTemplateEntity.setIntro("测试介绍1");
		messageTemplateEntity.setOpentype(0);
		messageTemplateEntity.setOpenurl("http://test1.com");
		messageTemplateEntity.setPublish_time(currDate);
		messageTemplateEntity.setPublish_type(0);
		messageTemplateEntity.setPush_crowd(1);
		messageTemplateEntity.setSend(1);
		messageTemplateEntity.setStatus(1);
		messageTemplateEntity.setStatus_adminid(11L);
		messageTemplateEntity.setStatus_reason("");
		messageTemplateEntity.setStatus_time(currDate);
		messageTemplateEntity.setTitle(title);
		messageTemplateEntity.setType(1);
		messageTemplateEntity.setUpdate_adminid(11L);
		messageTemplateEntity.setUpdate_time(currDate);
		
		return messageTemplateEntity;
	}

}

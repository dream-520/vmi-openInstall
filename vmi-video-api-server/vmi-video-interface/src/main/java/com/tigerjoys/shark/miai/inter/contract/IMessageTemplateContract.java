package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  系统消息[t_message_template]表 接口类
 * @author chengang
 * @Date 2017-05-17 10:31:09
 *
 */
public interface IMessageTemplateContract extends BaseContract<MessageTemplateEntity> {
	
	public List<MessageTemplateEntity> getUserMessages(String publish, String create);
	
	public List<MessageTemplateEntity> getAnchorMessages(String publish, String create);
	
}

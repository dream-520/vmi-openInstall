package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IMessagePushTemplateContract;
import com.tigerjoys.shark.miai.inter.entity.MessagePushTemplateEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.MessagePushTemplateMapper;

/**
 * 数据库中  系统消息[t_message_push_template]表 接口实现类
 * @author shiming
 * @Date 2019-10-14 15:44:34
 *
 */
@Repository
public class MessagePushTemplateContractImpl extends AbstractBaseContract<MessagePushTemplateEntity , MessagePushTemplateMapper> implements IMessagePushTemplateContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IMessageTemplateContract;
import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.MessageTemplateMapper;

/**
 * 数据库中  系统消息[t_message_template]表 接口实现类
 * @author chengang
 * @Date 2017-05-17 10:31:09
 *
 */
@Repository
public class MessageTemplateContractImpl extends AbstractBaseContract<MessageTemplateEntity , MessageTemplateMapper> implements IMessageTemplateContract {
	
	public List<MessageTemplateEntity> getUserMessages(String publish, String create) {
		return mapper.getUserMessages(publish, create);
	}

	@Override
	public List<MessageTemplateEntity> getAnchorMessages(String publish, String create) {
		return mapper.getAnchorMessages(publish, create);
	}
}

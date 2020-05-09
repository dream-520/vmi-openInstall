package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IMessageUserReadContract;
import com.tigerjoys.shark.miai.inter.entity.MessageUserReadEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.MessageUserReadMapper;

/**
 * 数据库中  用户阅读消息条数记录[t_message_user_read]表 接口实现类
 * @author shiming
 * @Date 2019-07-09 17:25:45
 *
 */
@Repository
public class MessageUserReadContractImpl extends AbstractBaseContract<MessageUserReadEntity , MessageUserReadMapper> implements IMessageUserReadContract {
	
}

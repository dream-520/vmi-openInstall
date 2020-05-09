package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppSendMessageUserContract;
import com.tigerjoys.shark.miai.inter.entity.AppSendMessageUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppSendMessageUserMapper;

/**
 * 数据库中  客服消息历史表[t_app_send_message_user]表 接口实现类
 * @author shiming
 * @Date 2019-09-06 16:29:41
 *
 */
@Repository
public class AppSendMessageUserContractImpl extends AbstractBaseContract<AppSendMessageUserEntity , AppSendMessageUserMapper> implements IAppSendMessageUserContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppStartMsgUserContract;
import com.tigerjoys.shark.miai.inter.entity.AppStartMsgUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppStartMsgUserMapper;

/**
 * 数据库中  用户发送消息的情况[t_app_start_msg_user]表 接口实现类
 * @author shiming
 * @Date 2019-01-04 16:33:58
 *
 */
@Repository
public class AppStartMsgUserContractImpl extends AbstractBaseContract<AppStartMsgUserEntity , AppStartMsgUserMapper> implements IAppStartMsgUserContract {

	@Override
	public AppStartMsgUserEntity lockByUserId(long userId) {
		return mapper.lockByUserId(userId);
	}
	
}

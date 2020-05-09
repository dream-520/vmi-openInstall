package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AppStartMsgUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户发送消息的情况[t_app_start_msg_user]表 接口类
 * @author shiming
 * @Date 2019-01-04 16:33:58
 *
 */
public interface IAppStartMsgUserContract extends BaseContract<AppStartMsgUserEntity> {
	
	/**
	 * 当前用户消息加锁
	 * @param userId
	 * @return
	 */
	public abstract AppStartMsgUserEntity lockByUserId(long userId);
	
}

package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AppMsgSceneUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_app_msg_scene_user]表 接口类
 * @author shiming
 * @Date 2019-09-11 15:01:54
 *
 */
public interface IAppMsgSceneUserContract extends BaseContract<AppMsgSceneUserEntity> {
	
	/**
	 * 当前用户消息加锁
	 * @param userId
	 * @return
	 */
	public abstract AppMsgSceneUserEntity lockByUserId(long userId);
}

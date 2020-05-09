package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppMsgSceneUserContract;
import com.tigerjoys.shark.miai.inter.entity.AppMsgSceneUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppMsgSceneUserMapper;

/**
 * 数据库中  [t_app_msg_scene_user]表 接口实现类
 * @author shiming
 * @Date 2019-09-11 15:01:54
 *
 */
@Repository
public class AppMsgSceneUserContractImpl extends AbstractBaseContract<AppMsgSceneUserEntity , AppMsgSceneUserMapper> implements IAppMsgSceneUserContract {

	@Override
	public AppMsgSceneUserEntity lockByUserId(long userId) {
		return mapper.lockByUserId(userId);
	}
	
}

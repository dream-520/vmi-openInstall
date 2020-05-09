package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppMsgSceneDetailContract;
import com.tigerjoys.shark.miai.inter.entity.AppMsgSceneDetailEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppMsgSceneDetailMapper;

/**
 * 数据库中  [t_app_msg_scene_detail]表 接口实现类
 * @author shiming
 * @Date 2019-09-11 15:01:54
 *
 */
@Repository
public class AppMsgSceneDetailContractImpl extends AbstractBaseContract<AppMsgSceneDetailEntity , AppMsgSceneDetailMapper> implements IAppMsgSceneDetailContract {
	
}

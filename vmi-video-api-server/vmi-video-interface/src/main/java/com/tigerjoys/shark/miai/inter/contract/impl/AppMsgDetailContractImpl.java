package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppMsgDetailContract;
import com.tigerjoys.shark.miai.inter.entity.AppMsgDetailEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppMsgDetailMapper;

/**
 * 数据库中  全网消息详情表[t_app_msg_detail]表 接口实现类
 * @author shiming
 * @Date 2019-07-23 10:40:19
 *
 */
@Repository
public class AppMsgDetailContractImpl extends AbstractBaseContract<AppMsgDetailEntity , AppMsgDetailMapper> implements IAppMsgDetailContract {
	
}

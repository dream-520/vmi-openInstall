package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppStartSendMsgContract;
import com.tigerjoys.shark.miai.inter.entity.AppStartSendMsgEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppStartSendMsgMapper;

/**
 * 数据库中  [t_app_start_send_msg]表 接口实现类
 * @author shiming
 * @Date 2019-01-04 16:33:58
 *
 */
@Repository
public class AppStartSendMsgContractImpl extends AbstractBaseContract<AppStartSendMsgEntity , AppStartSendMsgMapper> implements IAppStartSendMsgContract {
	
}

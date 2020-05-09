package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ISmsDelayQueueContract;
import com.tigerjoys.shark.miai.inter.entity.SmsDelayQueueEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.SmsDelayQueueMapper;

/**
 * 数据库中  短信发送延迟队列表[t_sms_delay_queue]表 接口实现类
 * @author chengang
 * @Date 2017-11-10 08:27:51
 *
 */
@Repository
public class SmsDelayQueueContractImpl extends AbstractBaseContract<SmsDelayQueueEntity , SmsDelayQueueMapper> implements ISmsDelayQueueContract {
	
}

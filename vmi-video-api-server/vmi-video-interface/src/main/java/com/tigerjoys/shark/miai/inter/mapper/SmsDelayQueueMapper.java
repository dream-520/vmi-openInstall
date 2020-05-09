package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.SmsDelayQueueEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  短信发送延迟队列表[t_sms_delay_queue]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-11-10 08:27:51
 *
 */
@Producer(entityType=SmsDelayQueueEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface SmsDelayQueueMapper extends BaseMapper<SmsDelayQueueEntity> {
    
}
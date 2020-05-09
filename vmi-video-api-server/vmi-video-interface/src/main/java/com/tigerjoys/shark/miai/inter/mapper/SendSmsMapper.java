package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.SendSmsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  短信发送记录表[t_send_sms]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-08-18 10:29:55
 *
 */
@Producer(entityType=SendSmsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface SendSmsMapper extends BaseMapper<SendSmsEntity> {
    
}
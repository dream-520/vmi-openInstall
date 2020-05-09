package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppStartSendMsgEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_app_start_send_msg]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-01-04 16:33:58
 *
 */
@Producer(entityType=AppStartSendMsgEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppStartSendMsgMapper extends BaseMapper<AppStartSendMsgEntity> {
    
}
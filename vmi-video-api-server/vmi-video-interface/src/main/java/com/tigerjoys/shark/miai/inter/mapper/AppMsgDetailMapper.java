package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppMsgDetailEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  全网消息详情表[t_app_msg_detail]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-23 10:40:19
 *
 */
@Producer(entityType=AppMsgDetailEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppMsgDetailMapper extends BaseMapper<AppMsgDetailEntity> {
    
}
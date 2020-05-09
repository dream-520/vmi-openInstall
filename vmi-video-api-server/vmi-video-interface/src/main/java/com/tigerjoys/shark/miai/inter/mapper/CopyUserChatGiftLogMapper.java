package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CopyUserChatGiftLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  礼物消费记录[t_copy_user_chat_gift_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-12-17 19:03:27
 *
 */
@Producer(entityType=CopyUserChatGiftLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface CopyUserChatGiftLogMapper extends BaseMapper<CopyUserChatGiftLogEntity> {
    
}
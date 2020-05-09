package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户聊天送礼物数量统计表[t_user_chat_gift_statistics]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-07-09 13:53:47
 *
 */
@Producer(entityType=UserChatGiftStatisticsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserChatGiftStatisticsMapper extends BaseMapper<UserChatGiftStatisticsEntity> {
    
}
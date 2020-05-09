package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftStatisticsFakeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户聊天送礼物数量统计表[t_user_chat_gift_statistics_fake]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-07-17 16:50:03
 *
 */
@Producer(entityType=UserChatGiftStatisticsFakeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserChatGiftStatisticsFakeMapper extends BaseMapper<UserChatGiftStatisticsFakeEntity> {
    
}
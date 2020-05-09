package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserBuyWatchPopLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户购买弹窗记录[t_user_buy_watch_pop_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-12-04 20:21:31
 *
 */
@Producer(entityType=UserBuyWatchPopLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserBuyWatchPopLogMapper extends BaseMapper<UserBuyWatchPopLogEntity> {
    
}
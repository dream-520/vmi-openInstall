package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondPriceWatchLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户查看钻石价格列表初始记录[t_user_diamond_price_watch_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-07-26 15:24:32
 *
 */
@Producer(entityType=UserDiamondPriceWatchLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserDiamondPriceWatchLogMapper extends BaseMapper<UserDiamondPriceWatchLogEntity> {
    
}
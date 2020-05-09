package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarUserPrizeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_activity_star_user_prize]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Producer(entityType=ActivityStarUserPrizeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ActivityStarUserPrizeMapper extends BaseMapper<ActivityStarUserPrizeEntity> {
    
}
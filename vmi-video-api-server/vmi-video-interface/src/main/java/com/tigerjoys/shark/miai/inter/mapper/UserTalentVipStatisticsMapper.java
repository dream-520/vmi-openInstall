package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTalentVipStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户达人收入统计[t_user_talent_vip_statistics]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-11-27 11:31:09
 *
 */
@Producer(entityType=UserTalentVipStatisticsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserTalentVipStatisticsMapper extends BaseMapper<UserTalentVipStatisticsEntity> {
    
}
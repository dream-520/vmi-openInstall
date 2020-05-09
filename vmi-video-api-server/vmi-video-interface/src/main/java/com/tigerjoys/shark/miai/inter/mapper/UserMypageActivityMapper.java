package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserMypageActivityEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  我的页面活动表[t_user_mypage_activity]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-09-12 11:25:26
 *
 */
@Producer(entityType=UserMypageActivityEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserMypageActivityMapper extends BaseMapper<UserMypageActivityEntity> {
    
}
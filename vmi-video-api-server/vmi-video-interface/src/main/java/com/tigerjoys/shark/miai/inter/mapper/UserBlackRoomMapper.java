package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserBlackRoomEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户小黑屋处理表[t_user_black_room]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-11-12 11:07:36
 *
 */
@Producer(entityType=UserBlackRoomEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserBlackRoomMapper extends BaseMapper<UserBlackRoomEntity> {
    
}
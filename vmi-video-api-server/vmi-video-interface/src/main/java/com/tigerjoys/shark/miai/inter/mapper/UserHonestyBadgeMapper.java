package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserHonestyBadgeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户诚信徽章表[t_user_honesty_badge]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-13 16:52:00
 *
 */
@Producer(entityType=UserHonestyBadgeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserHonestyBadgeMapper extends BaseMapper<UserHonestyBadgeEntity> {
    
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserHonestyBadgeEntity
	 */
	@Select("select * from t_user_honesty_badge where user_id = #{userId} for update")
	public abstract UserHonestyBadgeEntity lockByUserId(long userId);
}
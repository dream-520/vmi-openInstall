package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户（购买的）红花账户[t_red_flower_account]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-12-20 15:29:35
 *
 */
@Producer(entityType=RedFlowerAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface RedFlowerAccountMapper extends BaseMapper<RedFlowerAccountEntity> {
    
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserHonestyBadgeEntity
	 */
	@Select("select * from t_red_flower_account where user_id = #{userId} for update")
	public abstract RedFlowerAccountEntity lockByUserId(long userId);
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserWeekcardAccountEntity;

/**
 * 数据库  用户周卡账户表[t_user_weekcard_account]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-11-13 14:10:56
 *
 */
@Producer(entityType=UserWeekcardAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserWeekcardAccountMapper extends BaseMapper<UserWeekcardAccountEntity> {
    
	/**
	 * 根据用户ID,和礼物Id加锁查询获得对象  
	 * @param userId - Long
	 * @return UserHonestyBadgeEntity
	 */
	@Select("select * from t_user_weekcard_account where userid = #{userId} and card_id = #{cardId} for update")
	public abstract UserWeekcardAccountEntity lockByUserId(@Param("userId") long userId,@Param("cardId") long cardId);
}
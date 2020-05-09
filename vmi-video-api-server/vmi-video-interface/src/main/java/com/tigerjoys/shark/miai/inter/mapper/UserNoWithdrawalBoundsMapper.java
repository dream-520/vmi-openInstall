package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserNoWithdrawalBoundsEntity;

/**
 * 数据库  用户不可提现收益限额[t_user_no_withdrawal_bounds]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-08-05 14:15:28
 *
 */
@Producer(entityType=UserNoWithdrawalBoundsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserNoWithdrawalBoundsMapper extends BaseMapper<UserNoWithdrawalBoundsEntity> {
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param user - Long
	 * @return UserNoWithdrawalBoundsEntity
	 */
	@Select("select * from t_user_no_withdrawal_bounds where user_id = #{userId} and type = #{type} for update")
	public abstract UserNoWithdrawalBoundsEntity lockByUserId(@Param("userId") long userId, @Param("type") int type);
}
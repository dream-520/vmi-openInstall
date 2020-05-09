package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserCreditScoreEntity;

/**
 * 数据库  用户信用分账户[t_user_credit_score]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2017-08-14 18:43:02
 *
 */
@Producer(entityType=UserCreditScoreEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserCreditScoreMapper extends BaseMapper<UserCreditScoreEntity> {
	/**
	 * 根据用户ID获得对象  
	 * @param user - Long
	 * @return UserCreditScoreEntity
	 */
	@Select("select * from t_user_credit_score where userid = #{userid} for update")
	public abstract UserCreditScoreEntity lockByUserId(long userid);
}
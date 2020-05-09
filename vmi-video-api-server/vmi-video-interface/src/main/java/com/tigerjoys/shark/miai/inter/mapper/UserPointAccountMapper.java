package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserPointAccountEntity;

/**
 * 数据库  用户积分账户[t_user_point_account]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-09-05 11:23:23
 *
 */
@Producer(entityType=UserPointAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserPointAccountMapper extends BaseMapper<UserPointAccountEntity> {
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserDiamondAccountEntity
	 */
	@Select("select * from t_user_point_account where userid = #{userid} for update")
	public abstract UserPointAccountEntity lockByUserid(long userid);
	
}
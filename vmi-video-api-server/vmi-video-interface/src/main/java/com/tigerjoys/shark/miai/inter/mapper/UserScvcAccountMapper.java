package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserScvcAccountEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户SCVC账户[t_user_scvc_account]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:24
 *
 */
@Producer(entityType=UserScvcAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserScvcAccountMapper extends BaseMapper<UserScvcAccountEntity> {
    
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserScvcAccountEntity
	 */
	@Select("select * from t_user_scvc_account where user_id = #{userId} for update")
	public UserScvcAccountEntity lockByUserId(long userId);
}
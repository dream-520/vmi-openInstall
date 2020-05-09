package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTariffeAccountEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户话费账户[t_user_tariffe_account]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-12-10 13:55:12
 *
 */
@Producer(entityType=UserTariffeAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserTariffeAccountMapper extends BaseMapper<UserTariffeAccountEntity> {
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserDiamondAccountEntity
	 */
	@Select("select * from t_user_tariffe_account where userid = #{userid} for update")
	public abstract UserTariffeAccountEntity lockByUserid(long userid);
    
}
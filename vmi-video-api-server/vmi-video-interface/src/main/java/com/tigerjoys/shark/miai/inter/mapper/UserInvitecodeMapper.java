package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserInvitecodeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_user_invitecode]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-10-21 17:05:26
 *
 */
@Producer(entityType=UserInvitecodeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserInvitecodeMapper extends BaseMapper<UserInvitecodeEntity> {
	/**
	 * 根据状态加锁查询获得对象  
	 * @return UserInvitecodeEntity
	 */
	@Select("select * from t_user_invitecode where status=0 ORDER BY id LIMIT 1 for update ")
	public abstract UserInvitecodeEntity lockByStatus();
}
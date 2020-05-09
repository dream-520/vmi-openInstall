package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.VUserOnlineEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_v_user_online]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Producer(entityType=VUserOnlineEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface VUserOnlineMapper extends BaseMapper<VUserOnlineEntity> {
    
	@Select("select distinct a.userid from t_v_user_online a where a.create_time < #{today} and a.create_time >= #{yesterday}")
	public List<Long> loadUserIds(@Param("today") String today, @Param("yesterday") String yesterday);
	
	@Select("select * from t_v_user_online a where a.create_time < #{today} and a.create_time >= #{yesterday} group by a.userid")
	public List<VUserOnlineEntity> loadUsers(@Param("today") String today, @Param("yesterday") String yesterday);
}
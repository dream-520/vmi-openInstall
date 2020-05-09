package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AMOnlineEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_a_m_online]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-11-14 16:14:24
 *
 */
@Producer(entityType=AMOnlineEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface AMOnlineMapper extends BaseMapper<AMOnlineEntity> {
    
	@Select("select COUNT(distinct a.useridx) from t_a_m_online a where a.create_time < #{start} and a.create_time >= #{end} and a.type=#{type}")
	public int onlineNum(@Param("start") String start, @Param("end") String end, @Param("type") int type);
	
	@Select("select COUNT(distinct a.useridx) from t_a_m_online a where a.create_time < #{start} and a.create_time >= #{end} and a.onlineState = 4 and a.type=#{type}")
	public int talkingNum(@Param("start") String start, @Param("end") String end, @Param("type") int type);
	
	@Select("select distinct a.useridx from t_a_m_online a where a.create_time < #{start} and a.create_time >= #{end} and a.onlineState = 4  and a.type=#{type}")
	public List<Long> loadUserIds(@Param("start") String start, @Param("end") String end, @Param("type") int type);
	
}
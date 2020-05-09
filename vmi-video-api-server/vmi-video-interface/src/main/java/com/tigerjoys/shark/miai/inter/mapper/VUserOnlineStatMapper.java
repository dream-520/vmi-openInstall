package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.VUserOnlineStatEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_v_user_online_stat]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Producer(entityType=VUserOnlineStatEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface VUserOnlineStatMapper extends BaseMapper<VUserOnlineStatEntity> {
    
	@Select("select distinct a.userid from t_v_user_online_stat a where a.createTime = #{yesterday}")
	public List<Long> loadUserIds(@Param("yesterday") String yesterday);
	
}
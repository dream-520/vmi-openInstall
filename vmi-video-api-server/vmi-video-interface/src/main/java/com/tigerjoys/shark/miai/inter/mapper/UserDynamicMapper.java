package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  动态表[t_user_dynamic]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Producer(entityType=UserDynamicEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserDynamicMapper extends BaseMapper<UserDynamicEntity> {
    
	@Select("SELECT t_d.* FROM t_user_dynamic AS t_d INNER JOIN (SELECT friendid FROM t_user_friends WHERE userid = #{userid,jdbcType=BIGINT}) AS t_u ON t_d.userid = t_u.friendid WHERE t_d.create_time < #{stamp,jdbcType=VARCHAR} AND t_d.state = 1 ORDER BY create_time DESC LIMIT #{limit,jdbcType=BIGINT}")
	public List<UserDynamicEntity> findAttentionDynamic(@Param("userid")long userid, @Param("stamp")String stamp, @Param("limit")long limit);

}
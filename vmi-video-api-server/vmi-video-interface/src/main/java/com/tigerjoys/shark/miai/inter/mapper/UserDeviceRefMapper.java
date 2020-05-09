package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.ProducerResult;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserDeviceRefEntity;

/**
 * 数据库  注册设备跟直播用户的关联表[t_user_device_ref]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-04-12 11:43:23
 *
 */
@Producer(entityType=UserDeviceRefEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserDeviceRefMapper extends BaseMapper<UserDeviceRefEntity> {
	
	/**
	 * 查找设备和用户的关系
	 * @param deviceid - 设备ID
	 * @param userid - 用户ID
	 * @return UserDeviceRefEntity
	 */
	@Select("SELECT * FROM t_user_device_ref WHERE deviceid = #{deviceid,jdbcType=BIGINT} AND userid = #{userid,jdbcType=BIGINT}")
	@ProducerResult
	public UserDeviceRefEntity findUserDeviceRef(@Param("deviceid")long deviceid, @Param("userid")long userid);
    
}
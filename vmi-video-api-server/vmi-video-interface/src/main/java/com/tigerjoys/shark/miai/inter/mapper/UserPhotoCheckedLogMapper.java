package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoCheckedLogEntity;

/**
 * 数据库  用户付费查看用户信息记录[t_user_photo_checked_log]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2020-02-24 16:47:47
 *
 */
@Producer(entityType=UserPhotoCheckedLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserPhotoCheckedLogMapper extends BaseMapper<UserPhotoCheckedLogEntity> {
	
	/**
	 * 查询用户付费作品记录
	 * @param userId - 用户ID
	 * @param photoId - 作品ID
	 * @param type - 作品类型
	 * @return UserPhotoCheckedLogEntity
	 */
	@Select("select * from t_user_photo_checked_log where user_id = #{userId} and photo_id = #{photoId} and type = #{type} for update")
	public UserPhotoCheckedLogEntity lockByUserId(@Param("userId")long userId, @Param("photoId")long photoId, @Param("type")int type);
    
}
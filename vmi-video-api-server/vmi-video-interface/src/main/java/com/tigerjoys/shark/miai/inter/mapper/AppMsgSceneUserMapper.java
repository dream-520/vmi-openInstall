package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppMsgSceneUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_app_msg_scene_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-11 15:01:54
 *
 */
@Producer(entityType=AppMsgSceneUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppMsgSceneUserMapper extends BaseMapper<AppMsgSceneUserEntity> {
    
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserHonestyBadgeEntity
	 */
	@Select("select * from t_app_msg_scene_user where userid = #{userId} for update")
	public abstract AppMsgSceneUserEntity lockByUserId(long userId);
}
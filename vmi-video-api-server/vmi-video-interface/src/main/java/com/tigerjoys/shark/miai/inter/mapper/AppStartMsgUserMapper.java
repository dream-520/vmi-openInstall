package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppStartMsgUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户发送消息的情况[t_app_start_msg_user]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-01-04 16:33:58
 *
 */
@Producer(entityType=AppStartMsgUserEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppStartMsgUserMapper extends BaseMapper<AppStartMsgUserEntity> {
    
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return AppStartMsgUserEntity
	 */
	@Select("select * from t_app_start_msg_user where userid = #{userId} for update")
	public abstract AppStartMsgUserEntity lockByUserId(long userId);
	
}
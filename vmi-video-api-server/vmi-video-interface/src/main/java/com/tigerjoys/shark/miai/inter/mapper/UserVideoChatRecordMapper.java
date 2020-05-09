package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserVideoChatRecordEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户视频会话记录[t_user_video_chat_record]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:25
 *
 */
@Producer(entityType=UserVideoChatRecordEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserVideoChatRecordMapper extends BaseMapper<UserVideoChatRecordEntity> {
    
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param id - Long
	 * @return UserVideoChatRecordEntity
	 */
	@Select("select * from t_user_video_chat_record where id = #{id} for update")
	public UserVideoChatRecordEntity lockById(long id);
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftBoxEntity;

/**
 * 数据库  用户礼物盒[t_user_chat_gift_box]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-10-30 11:55:38
 *
 */
@Producer(entityType=UserChatGiftBoxEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserChatGiftBoxMapper extends BaseMapper<UserChatGiftBoxEntity> {
    
	/**
	 * 根据用户ID,和礼物Id加锁查询获得对象  
	 * @param userId - Long
	 * @return UserHonestyBadgeEntity
	 */
	@Select("select * from t_user_chat_gift_box where user_id = #{userId} and gift_id = #{giftId} for update")
	public abstract UserChatGiftBoxEntity lockByUserId(@Param("userId") long userId,@Param("giftId") long giftId);
}
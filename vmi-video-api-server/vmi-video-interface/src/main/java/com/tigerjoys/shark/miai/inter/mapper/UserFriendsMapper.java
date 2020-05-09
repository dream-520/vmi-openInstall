package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.SelectProducer;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.inter.entity.UserFriendsEntity;
import com.tigerjoys.shark.miai.inter.provider.UserFriendsSqlProvider;

/**
 * 数据库  用户好友表[t_user_friends]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-04-14 11:14:35
 *
 */
@Producer(entityType=UserFriendsEntity.class,providerType=UserFriendsSqlProvider.class)
@Mapper
public interface UserFriendsMapper extends BaseMapper<UserFriendsEntity> {
	
	/**
	 * 获取用户ID列表
	 * @param pageModel - PageModel
	 * @return List<Long>
	 */
	@SelectProducer
	public abstract List<Long> loadUserIds(PageModel pageModel);
	
	/**
	 * 获取好友ID列表
	 * @param pageModel - PageModel
	 * @return List<Long>
	 */
	@SelectProducer
	public abstract List<Long> loadFriendIds(PageModel pageModel);
    
}
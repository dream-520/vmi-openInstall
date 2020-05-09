package com.tigerjoys.shark.miai.inter.contract;

import java.util.List;

import com.tigerjoys.shark.miai.inter.entity.UserFriendsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;

/**
 * 数据库中  用户好友表[t_user_friends]表 接口类
 * @author chengang
 * @Date 2017-04-14 11:14:35
 *
 */
public interface IUserFriendsContract extends BaseContract<UserFriendsEntity> {
	
	/**
	 * 获取用户ID列表
	 * @param pageModel - PageModel
	 * @return List<Long>
	 * @throws Exception
	 */
	public List<Long> loadUserIds(PageModel pageModel) throws Exception;
	
	/**
	 * 获取好友ID列表
	 * @param pageModel - PageModel
	 * @return List<Long>
	 * @throws Exception
	 */
	public List<Long> loadFriendIds(PageModel pageModel) throws Exception;
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserFriendsContract;
import com.tigerjoys.shark.miai.inter.entity.UserFriendsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.inter.mapper.UserFriendsMapper;

/**
 * 数据库中  用户好友表[t_user_friends]表 接口实现类
 * @author chengang
 * @Date 2017-04-14 11:14:35
 *
 */
@Repository
public class UserFriendsContractImpl extends AbstractBaseContract<UserFriendsEntity , UserFriendsMapper> implements IUserFriendsContract {

	@Override
	public List<Long> loadUserIds(PageModel pageModel) throws Exception {
		return mapper.loadUserIds(pageModel);
	}

	@Override
	public List<Long> loadFriendIds(PageModel pageModel) throws Exception {
		return mapper.loadFriendIds(pageModel);
	}
	
}

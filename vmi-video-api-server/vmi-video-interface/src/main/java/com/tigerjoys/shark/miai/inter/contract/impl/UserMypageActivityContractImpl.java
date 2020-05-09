package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserMypageActivityContract;
import com.tigerjoys.shark.miai.inter.entity.UserMypageActivityEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserMypageActivityMapper;

/**
 * 数据库中  我的页面活动表[t_user_mypage_activity]表 接口实现类
 * @author lipeng
 * @Date 2017-09-12 11:25:26
 *
 */
@Repository
public class UserMypageActivityContractImpl extends AbstractBaseContract<UserMypageActivityEntity , UserMypageActivityMapper> implements IUserMypageActivityContract {
	
}

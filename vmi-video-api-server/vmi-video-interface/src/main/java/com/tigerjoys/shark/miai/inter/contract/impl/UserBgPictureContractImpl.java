package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.contract.IUserBgPictureContract;
import com.tigerjoys.shark.miai.inter.entity.UserBgPictureEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserBgPictureMapper;

/**
 * 数据库中  用户背景图片表[t_user_bg_picture]表 接口实现类
 * @author lipeng
 * @Date 2017-12-21 16:51:37
 *
 */
@Repository
public class UserBgPictureContractImpl extends RedisCacheContract<UserBgPictureEntity , UserBgPictureMapper> implements IUserBgPictureContract {
	
}

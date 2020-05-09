package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserPhotoResourceMapper;

/**
 * 数据库中  动态资源表[t_user_photo_resource]表 接口实现类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Repository
public class UserPhotoResourceContractImpl extends AbstractBaseContract<UserPhotoResourceEntity , UserPhotoResourceMapper> implements IUserPhotoResourceContract {
	
}

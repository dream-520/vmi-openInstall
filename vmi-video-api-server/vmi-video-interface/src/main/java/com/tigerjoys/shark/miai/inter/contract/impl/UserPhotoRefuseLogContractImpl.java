package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserPhotoRefuseLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoRefuseLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserPhotoRefuseLogMapper;

/**
 * 数据库中  用户头像审核拒绝记录表[t_user_photo_refuse_log]表 接口实现类
 * @author lipeng
 * @Date 2019-12-14 16:36:00
 *
 */
@Repository
public class UserPhotoRefuseLogContractImpl extends AbstractBaseContract<UserPhotoRefuseLogEntity , UserPhotoRefuseLogMapper> implements IUserPhotoRefuseLogContract {
	
}

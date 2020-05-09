package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserVideoAuthContract;
import com.tigerjoys.shark.miai.inter.entity.UserVideoAuthEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserVideoAuthMapper;

/**
 * 数据库中  用户视频认证提交表[t_user_video_auth]表 接口实现类
 * @author lipeng
 * @Date 2017-09-19 10:03:42
 *
 */
@Repository
public class UserVideoAuthContractImpl extends AbstractBaseContract<UserVideoAuthEntity , UserVideoAuthMapper> implements IUserVideoAuthContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorSuperSubscribeUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorSuperSubscribeUserMapper;

/**
 * 数据库中  用户一键预约表[t_anchor_super_subscribe_user]表 接口实现类
 * @author shiming
 * @Date 2019-11-18 11:22:47
 *
 */
@Repository
public class AnchorSuperSubscribeUserContractImpl extends AbstractBaseContract<AnchorSuperSubscribeUserEntity , AnchorSuperSubscribeUserMapper> implements IAnchorSuperSubscribeUserContract {
	
}

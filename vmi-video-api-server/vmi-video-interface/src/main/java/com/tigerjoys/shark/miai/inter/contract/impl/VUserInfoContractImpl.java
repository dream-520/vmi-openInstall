package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVUserInfoContract;
import com.tigerjoys.shark.miai.inter.entity.VUserInfoEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VUserInfoMapper;

/**
 * 数据库中  [t_v_user_info]表 接口实现类
 * @author shiming
 * @Date 2018-10-10 14:21:21
 *
 */
@Repository
public class VUserInfoContractImpl extends AbstractBaseContract<VUserInfoEntity , VUserInfoMapper> implements IVUserInfoContract {
	
}

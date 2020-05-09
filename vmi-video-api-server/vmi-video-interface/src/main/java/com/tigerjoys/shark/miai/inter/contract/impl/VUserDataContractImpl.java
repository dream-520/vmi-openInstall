package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVUserDataContract;
import com.tigerjoys.shark.miai.inter.entity.VUserDataEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VUserDataMapper;

/**
 * 数据库中  [t_v_user_data]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Repository
public class VUserDataContractImpl extends AbstractBaseContract<VUserDataEntity , VUserDataMapper> implements IVUserDataContract {
	
}

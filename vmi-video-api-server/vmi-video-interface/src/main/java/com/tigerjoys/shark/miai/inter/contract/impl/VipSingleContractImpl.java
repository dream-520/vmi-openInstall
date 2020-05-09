package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVipSingleContract;
import com.tigerjoys.shark.miai.inter.entity.VipSingleEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VipSingleMapper;

/**
 * 数据库中  只允许购买一次VIP记录[t_vip_single]表 接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
@Repository
public class VipSingleContractImpl extends AbstractBaseContract<VipSingleEntity , VipSingleMapper> implements IVipSingleContract {
	
}

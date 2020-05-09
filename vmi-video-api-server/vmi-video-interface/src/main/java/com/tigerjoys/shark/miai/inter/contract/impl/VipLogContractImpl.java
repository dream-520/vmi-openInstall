package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVipLogContract;
import com.tigerjoys.shark.miai.inter.entity.VipLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VipLogMapper;

/**
 * 数据库中  用户VIP充值记录[t_vip_log]表 接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
@Repository
public class VipLogContractImpl extends AbstractBaseContract<VipLogEntity , VipLogMapper> implements IVipLogContract {
	
}

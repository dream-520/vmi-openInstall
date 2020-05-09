package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorSuperSubscribeContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorSuperSubscribeMapper;

/**
 * 数据库中  金牌预约大V[t_anchor_super_subscribe]表 接口实现类
 * @author shiming
 * @Date 2019-11-16 18:52:02
 *
 */
@Repository
public class AnchorSuperSubscribeContractImpl extends AbstractBaseContract<AnchorSuperSubscribeEntity , AnchorSuperSubscribeMapper> implements IAnchorSuperSubscribeContract {
	
}

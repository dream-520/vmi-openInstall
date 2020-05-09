package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorSubscribeContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorSubscribeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorSubscribeMapper;

/**
 * 数据库中  [t_anchor_subscribe]表 接口实现类
 * @author shiming
 * @Date 2019-11-11 14:01:46
 *
 */
@Repository
public class AnchorSubscribeContractImpl extends AbstractBaseContract<AnchorSubscribeEntity , AnchorSubscribeMapper> implements IAnchorSubscribeContract {
	
}

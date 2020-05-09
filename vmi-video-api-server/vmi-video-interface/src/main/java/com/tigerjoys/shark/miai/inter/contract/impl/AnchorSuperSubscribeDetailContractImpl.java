package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorSuperSubscribeDetailContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeDetailEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorSuperSubscribeDetailMapper;

/**
 * 数据库中  用户一键预约详情表[t_anchor_super_subscribe_detail]表 接口实现类
 * @author shiming
 * @Date 2019-11-18 14:45:37
 *
 */
@Repository
public class AnchorSuperSubscribeDetailContractImpl extends AbstractBaseContract<AnchorSuperSubscribeDetailEntity , AnchorSuperSubscribeDetailMapper> implements IAnchorSuperSubscribeDetailContract {
	
}

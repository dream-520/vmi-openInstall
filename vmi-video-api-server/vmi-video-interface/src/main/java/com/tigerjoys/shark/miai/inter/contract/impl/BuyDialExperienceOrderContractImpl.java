package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IBuyDialExperienceOrderContract;
import com.tigerjoys.shark.miai.inter.entity.BuyDialExperienceOrderEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.BuyDialExperienceOrderMapper;

/**
 * 数据库中  购买畅聊订单[t_buy_dial_experience_order]表 接口实现类
 * @author yangjunming
 * @Date 2019-11-29 15:30:47
 *
 */
@Repository
public class BuyDialExperienceOrderContractImpl extends AbstractBaseContract<BuyDialExperienceOrderEntity , BuyDialExperienceOrderMapper> implements IBuyDialExperienceOrderContract {
	
}

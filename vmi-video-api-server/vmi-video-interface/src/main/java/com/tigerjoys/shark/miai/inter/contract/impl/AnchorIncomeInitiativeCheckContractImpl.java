package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorIncomeInitiativeCheckContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorIncomeInitiativeCheckEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorIncomeInitiativeCheckMapper;

/**
 * 数据库中  主播主动拨打收益表[t_anchor_income_initiative_check]表 接口实现类
 * @author shiming
 * @Date 2019-06-25 14:42:20
 *
 */
@Repository
public class AnchorIncomeInitiativeCheckContractImpl extends AbstractBaseContract<AnchorIncomeInitiativeCheckEntity , AnchorIncomeInitiativeCheckMapper> implements IAnchorIncomeInitiativeCheckContract {
	
}

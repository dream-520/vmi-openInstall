package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorIncomeInitiativePayContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorIncomeInitiativePayEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorIncomeInitiativePayMapper;

/**
 * 数据库中  主播主动拨打充值分析表[t_anchor_income_initiative_pay]表 接口实现类
 * @author shiming
 * @Date 2019-06-25 14:42:20
 *
 */
@Repository
public class AnchorIncomeInitiativePayContractImpl extends AbstractBaseContract<AnchorIncomeInitiativePayEntity , AnchorIncomeInitiativePayMapper> implements IAnchorIncomeInitiativePayContract {
	
}

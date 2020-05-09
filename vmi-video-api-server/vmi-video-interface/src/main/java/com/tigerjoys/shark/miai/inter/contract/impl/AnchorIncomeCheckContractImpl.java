package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorIncomeCheckContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorIncomeCheckEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorIncomeCheckMapper;

/**
 * 数据库中  主播收益考核表[t_anchor_income_check]表 接口实现类
 * @author yangjunming
 * @Date 2019-04-18 17:07:09
 *
 */
@Repository
public class AnchorIncomeCheckContractImpl extends AbstractBaseContract<AnchorIncomeCheckEntity , AnchorIncomeCheckMapper> implements IAnchorIncomeCheckContract {
	
}

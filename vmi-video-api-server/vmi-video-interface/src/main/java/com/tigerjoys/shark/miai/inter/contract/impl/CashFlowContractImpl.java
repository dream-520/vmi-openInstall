package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICashFlowContract;
import com.tigerjoys.shark.miai.inter.entity.CashFlowEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CashFlowMapper;

/**
 * 数据库中  现金流表[t_cash_flow]表 接口实现类
 * @author chengang
 * @Date 2017-05-11 15:39:46
 *
 */
@Repository
public class CashFlowContractImpl extends AbstractBaseContract<CashFlowEntity , CashFlowMapper> implements ICashFlowContract {
	
}

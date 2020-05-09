package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICreditScoreConfigureContract;
import com.tigerjoys.shark.miai.inter.entity.CreditScoreConfigureEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CreditScoreConfigureMapper;

/**
 * 数据库中  信用分购买配置信息[t_credit_score_configure]表 接口实现类
 * @author liuman
 * @Date 2017-08-16 16:20:39
 *
 */
@Repository
public class CreditScoreConfigureContractImpl extends AbstractBaseContract<CreditScoreConfigureEntity , CreditScoreConfigureMapper> implements ICreditScoreConfigureContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IPayUserRecordContract;
import com.tigerjoys.shark.miai.inter.entity.PayUserRecordEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.PayUserRecordMapper;

/**
 * 数据库中  付费用户表[t_pay_user_record]表 接口实现类
 * @author lipeng
 * @Date 2019-10-17 10:29:50
 *
 */
@Repository
public class PayUserRecordContractImpl extends AbstractBaseContract<PayUserRecordEntity , PayUserRecordMapper> implements IPayUserRecordContract {
	
}

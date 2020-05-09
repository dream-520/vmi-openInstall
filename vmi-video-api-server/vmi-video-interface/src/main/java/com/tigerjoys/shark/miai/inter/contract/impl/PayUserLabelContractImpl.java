package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IPayUserLabelContract;
import com.tigerjoys.shark.miai.inter.entity.PayUserLabelEntity;
import com.tigerjoys.shark.miai.inter.mapper.PayUserLabelMapper;

/**
 * 数据库中  付费用户标签表[t_pay_user_label]表 接口实现类
 * @author lipeng
 * @Date 2019-10-22 16:36:28
 *
 */
@Repository
public class PayUserLabelContractImpl extends AbstractBaseContract<PayUserLabelEntity , PayUserLabelMapper> implements IPayUserLabelContract {
	
}

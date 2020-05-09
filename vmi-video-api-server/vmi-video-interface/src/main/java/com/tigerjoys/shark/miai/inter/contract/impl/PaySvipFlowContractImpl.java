package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IPaySvipFlowContract;
import com.tigerjoys.shark.miai.inter.entity.PaySvipFlowEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.PaySvipFlowMapper;

/**
 * 数据库中  支付高级会员购买流水表[t_pay_svip_flow]表 接口实现类
 * @author chengang
 * @Date 2018-01-26 13:34:41
 *
 */
@Repository
public class PaySvipFlowContractImpl extends AbstractBaseContract<PaySvipFlowEntity , PaySvipFlowMapper> implements IPaySvipFlowContract {
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserPaySendDiamondContract;
import com.tigerjoys.shark.miai.inter.entity.UserPaySendDiamondEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserPaySendDiamondMapper;

/**
 * 数据库中  累计充值超过500记录送钻记录[t_user_pay_send_diamond]表 接口实现类
 * @author shiming
 * @Date 2019-09-03 14:41:44
 *
 */
@Repository
public class UserPaySendDiamondContractImpl extends AbstractBaseContract<UserPaySendDiamondEntity , UserPaySendDiamondMapper> implements IUserPaySendDiamondContract {
	
}

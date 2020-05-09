package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICopyUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.CopyUserPayActionEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CopyUserPayActionMapper;

/**
 * 数据库中  用户支付记录[t_copy_user_pay_action]表 接口实现类
 * @author lipeng
 * @Date 2019-12-17 20:19:28
 *
 */
@Repository
public class CopyUserPayActionContractImpl extends AbstractBaseContract<CopyUserPayActionEntity , CopyUserPayActionMapper> implements ICopyUserPayActionContract {
	
}

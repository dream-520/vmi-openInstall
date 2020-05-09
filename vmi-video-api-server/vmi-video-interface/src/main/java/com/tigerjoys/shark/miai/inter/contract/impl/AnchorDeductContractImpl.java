package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorDeductContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorDeductEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorDeductMapper;

/**
 * 数据库中  [t_anchor_deduct]表 接口实现类
 * @author shiming
 * @Date 2019-09-20 19:28:15
 *
 */
@Repository
public class AnchorDeductContractImpl extends AbstractBaseContract<AnchorDeductEntity , AnchorDeductMapper> implements IAnchorDeductContract {
	
}

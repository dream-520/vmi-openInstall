package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAaPContract;
import com.tigerjoys.shark.miai.inter.entity.AaPEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AaPMapper;

/**
 * 数据库中  [t_aa_p]表 接口实现类
 * @author shiming
 * @Date 2018-11-01 18:58:30
 *
 */
@Repository
public class AaPContractImpl extends AbstractBaseContract<AaPEntity , AaPMapper> implements IAaPContract {
	
}

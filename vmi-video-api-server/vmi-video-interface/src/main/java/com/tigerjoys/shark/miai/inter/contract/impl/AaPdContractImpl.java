package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAaPdContract;
import com.tigerjoys.shark.miai.inter.entity.AaPdEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AaPdMapper;

/**
 * 数据库中  [t_aa_pd]表 接口实现类
 * @author shiming
 * @Date 2018-11-02 09:55:28
 *
 */
@Repository
public class AaPdContractImpl extends AbstractBaseContract<AaPdEntity , AaPdMapper> implements IAaPdContract {
	
}

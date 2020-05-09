package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAVContract;
import com.tigerjoys.shark.miai.inter.entity.AVEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AVMapper;

/**
 * 数据库中  [t_a_v]表 接口实现类
 * @author shiming
 * @Date 2018-11-06 16:11:06
 *
 */
@Repository
public class AVContractImpl extends AbstractBaseContract<AVEntity , AVMapper> implements IAVContract {
	
}

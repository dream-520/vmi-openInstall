package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ILaborContract;
import com.tigerjoys.shark.miai.inter.entity.LaborEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.LaborMapper;

/**
 * 数据库中  工会列表[t_labor]表 接口实现类
 * @author lipeng
 * @Date 2019-09-21 11:27:53
 *
 */
@Repository
public class LaborContractImpl extends AbstractBaseContract<LaborEntity , LaborMapper> implements ILaborContract {
	
}

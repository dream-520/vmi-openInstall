package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ITalentLevelContract;
import com.tigerjoys.shark.miai.inter.entity.TalentLevelEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.TalentLevelMapper;

/**
 * 数据库中  达人等级表[t_talent_level]表 接口实现类
 * @author chengang
 * @Date 2018-01-29 14:20:07
 *
 */
@Repository
public class TalentLevelContractImpl extends AbstractBaseContract<TalentLevelEntity , TalentLevelMapper> implements ITalentLevelContract {
	
}

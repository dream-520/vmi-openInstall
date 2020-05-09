package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ITalentPriceContract;
import com.tigerjoys.shark.miai.inter.entity.TalentPriceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.TalentPriceMapper;

/**
 * 数据库中  达人等级价格管理[t_talent_price]表 接口实现类
 * @author shiming
 * @Date 2017-12-19 18:20:42
 *
 */
@Repository
public class TalentPriceContractImpl extends AbstractBaseContract<TalentPriceEntity , TalentPriceMapper> implements ITalentPriceContract {
	
}

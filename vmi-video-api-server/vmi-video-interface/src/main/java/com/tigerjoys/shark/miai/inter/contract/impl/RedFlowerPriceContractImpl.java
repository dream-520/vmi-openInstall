package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRedFlowerPriceContract;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerPriceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RedFlowerPriceMapper;

/**
 * 数据库中  小红花价格列表[t_red_flower_price]表 接口实现类
 * @author shiming
 * @Date 2019-08-03 14:04:18
 *
 */
@Repository
public class RedFlowerPriceContractImpl extends AbstractBaseContract<RedFlowerPriceEntity , RedFlowerPriceMapper> implements IRedFlowerPriceContract {
	
}

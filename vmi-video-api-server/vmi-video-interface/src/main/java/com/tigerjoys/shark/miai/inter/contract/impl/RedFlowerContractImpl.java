package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRedFlowerContract;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerEntity;
import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.mapper.RedFlowerMapper;

/**
 * 数据库中  小红花价格列表[t_red_flower]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-12-20 14:44:38
 *
 */
@Repository
public class RedFlowerContractImpl extends RedisCacheContract<RedFlowerEntity , RedFlowerMapper> implements IRedFlowerContract {
	
}

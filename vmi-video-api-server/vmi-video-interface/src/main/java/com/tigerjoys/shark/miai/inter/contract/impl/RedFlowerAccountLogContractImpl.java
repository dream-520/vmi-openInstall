package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRedFlowerAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RedFlowerAccountLogMapper;

/**
 * 数据库中  小红花账户流水[t_red_flower_account_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-08-09 16:26:44
 *
 */
@Repository
public class RedFlowerAccountLogContractImpl extends AbstractBaseContract<RedFlowerAccountLogEntity , RedFlowerAccountLogMapper> implements IRedFlowerAccountLogContract {
	
}

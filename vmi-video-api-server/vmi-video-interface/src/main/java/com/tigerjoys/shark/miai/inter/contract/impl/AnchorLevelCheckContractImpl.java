package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorLevelCheckContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorLevelCheckEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorLevelCheckMapper;

/**
 * 数据库中  主播等级考核表[t_anchor_level_check]表 接口实现类
 * @author yangjunming
 * @Date 2019-08-27 14:33:50
 *
 */
@Repository
public class AnchorLevelCheckContractImpl extends AbstractBaseContract<AnchorLevelCheckEntity , AnchorLevelCheckMapper> implements IAnchorLevelCheckContract {
	
}

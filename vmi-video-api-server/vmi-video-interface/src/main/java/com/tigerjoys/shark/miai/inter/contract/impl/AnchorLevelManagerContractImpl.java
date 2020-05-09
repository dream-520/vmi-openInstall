package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorLevelManagerContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorLevelManagerEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorLevelManagerMapper;

/**
 * 数据库中  主播升级体系[t_anchor_level_manager]表 接口实现类
 * @author yangjunming
 * @Date 2019-08-27 11:24:01
 *
 */
@Repository
public class AnchorLevelManagerContractImpl extends AbstractBaseContract<AnchorLevelManagerEntity , AnchorLevelManagerMapper> implements IAnchorLevelManagerContract {
	@Override
	public AnchorLevelManagerEntity getStar(int star) throws Exception {
		return mapper.findByProperty("anchor_star", star);
	}
}

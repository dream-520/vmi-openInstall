package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorDefendContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorDefendEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorDefendMapper;

/**
 * 数据库中  用户守护主播表[t_anchor_defend]表 接口实现类
 * @author shiming
 * @Date 2019-10-04 20:37:29
 *
 */
@Repository
public class AnchorDefendContractImpl extends AbstractBaseContract<AnchorDefendEntity , AnchorDefendMapper> implements IAnchorDefendContract {
	
}

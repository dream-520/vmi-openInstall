package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorTagContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorTagEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorTagMapper;

/**
 * 数据库中  主播标签表[t_anchor_tag]表 接口实现类
 * @author shiming
 * @Date 2019-09-04 16:38:20
 *
 */
@Repository
public class AnchorTagContractImpl extends AbstractBaseContract<AnchorTagEntity , AnchorTagMapper> implements IAnchorTagContract {
	
}

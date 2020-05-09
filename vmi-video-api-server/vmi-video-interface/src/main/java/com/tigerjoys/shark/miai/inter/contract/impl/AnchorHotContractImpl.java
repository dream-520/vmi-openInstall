package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorHotContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorHotEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorHotMapper;

/**
 * 数据库中  ios推荐主播类别表[t_anchor_hot]表 接口实现类
 * @author shiming
 * @Date 2019-03-25 15:25:10
 *
 */
@Repository
public class AnchorHotContractImpl extends AbstractBaseContract<AnchorHotEntity , AnchorHotMapper> implements IAnchorHotContract {
	
}

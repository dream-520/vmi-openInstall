package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorContactContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorContactEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorContactMapper;

/**
 * 数据库中  主播对应的联系方式信息[t_anchor_contact]表 接口实现类
 * @author shiming
 * @Date 2020-01-04 19:55:17
 *
 */
@Repository
public class AnchorContactContractImpl extends AbstractBaseContract<AnchorContactEntity , AnchorContactMapper> implements IAnchorContactContract {
	
}

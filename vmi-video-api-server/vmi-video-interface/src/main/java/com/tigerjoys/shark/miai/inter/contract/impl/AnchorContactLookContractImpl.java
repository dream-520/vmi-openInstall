package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorContactLookContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorContactLookEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorContactLookMapper;

/**
 * 数据库中  用户查看主播联系方式记录表[t_anchor_contact_look]表 接口实现类
 * @author shiming
 * @Date 2020-01-06 16:50:09
 *
 */
@Repository
public class AnchorContactLookContractImpl extends AbstractBaseContract<AnchorContactLookEntity , AnchorContactLookMapper> implements IAnchorContactLookContract {
	
}

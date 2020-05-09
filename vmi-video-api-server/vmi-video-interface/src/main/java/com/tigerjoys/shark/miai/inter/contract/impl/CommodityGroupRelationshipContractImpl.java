package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICommodityGroupRelationshipContract;
import com.tigerjoys.shark.miai.inter.entity.CommodityGroupRelationshipEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CommodityGroupRelationshipMapper;

/**
 * 数据库中  用户商品关系表[t_commodity_group_relationship]表 接口实现类
 * @author lipeng
 * @Date 2018-12-10 14:35:58
 *
 */
@Repository
public class CommodityGroupRelationshipContractImpl extends AbstractBaseContract<CommodityGroupRelationshipEntity , CommodityGroupRelationshipMapper> implements ICommodityGroupRelationshipContract {
	
}

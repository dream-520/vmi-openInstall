package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVipCategoryContract;
import com.tigerjoys.shark.miai.inter.entity.VipCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VipCategoryMapper;

/**
 * 数据库中  VIP类型[t_vip_category]表 接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
@Repository
public class VipCategoryContractImpl extends AbstractBaseContract<VipCategoryEntity , VipCategoryMapper> implements IVipCategoryContract {

	@Override
	public int updateBuyNum(long id) throws Exception {
		
		return mapper.updateByStatement("buy_num=buy_num+1", "id="+id);
	}
	
}

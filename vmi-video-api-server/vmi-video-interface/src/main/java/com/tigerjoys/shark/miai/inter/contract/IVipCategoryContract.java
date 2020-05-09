package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.VipCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  VIP类型[t_vip_category]表 接口类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
public interface IVipCategoryContract extends BaseContract<VipCategoryEntity> {
	/**
	 * 购买数量加1
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int updateBuyNum(long id) throws Exception;
	
}

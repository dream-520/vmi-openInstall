package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.RechargeOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerOrderEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  小红花订单表[t_red_flower_order]表 接口类
 * @author yangjunming
 * @Date 2019-08-16 12:39:09
 *
 */
public interface IRedFlowerOrderContract extends BaseContract<RedFlowerOrderEntity> {
	public void insert(RedFlowerOrderEntity t) throws Exception;
	
}

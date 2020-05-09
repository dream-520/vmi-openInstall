package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AppointSiteOrderEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  场地订单表[t_appoint_site_order]表 接口类
 * @author yangjunming
 * @Date 2017-12-14 17:48:58
 *
 */
public interface IAppointSiteOrderContract extends BaseContract<AppointSiteOrderEntity> {
	/**
	 * 更新支付完成
	 * @param userid
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int updateCompeleteStatus(long id) throws Exception;
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IAppointSiteOrderContract;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteOrderEntity;
import com.tigerjoys.shark.miai.inter.mapper.AppointSiteOrderMapper;

/**
 * 数据库中  场地订单表[t_appoint_site_order]表 接口实现类
 * @author yangjunming
 * @Date 2017-12-14 17:48:58
 *
 */
@Repository
public class AppointSiteOrderContractImpl extends AbstractBaseContract<AppointSiteOrderEntity , AppointSiteOrderMapper> implements IAppointSiteOrderContract {

	@Override
	public int updateCompeleteStatus(long id) throws Exception {
		return mapper.updateByStatement("STATUS=1,update_time=now()", "order_id="+id);
	}
	
}

package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IVipContract;
import com.tigerjoys.shark.miai.inter.entity.VipEntity;
import com.tigerjoys.shark.miai.inter.mapper.VipMapper;

/**
 * 数据库中  用户VIP表[t_vip]表 接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
@Repository
public class VipContractImpl extends AbstractBaseContract<VipEntity , VipMapper> implements IVipContract {
	
	@Override
	public int updateVipTime(long userid, int days) throws Exception {
		return mapper.updateByStatement("update_time=NOW(),expire_time=(CASE WHEN expire_time>NOW() THEN DATE_SUB(expire_time,INTERVAL -"+days+" DAY) ELSE DATE_SUB(NOW(),INTERVAL -"+days+" DAY) END)", "userid="+userid);
	}
	
}

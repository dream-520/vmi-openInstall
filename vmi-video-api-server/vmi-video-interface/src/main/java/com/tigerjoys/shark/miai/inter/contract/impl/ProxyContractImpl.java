package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IProxyContract;
import com.tigerjoys.shark.miai.inter.entity.ProxyEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ProxyMapper;

/**
 * 数据库中 [t_proxy]表 接口实现类
 * 
 * @author yangjunming
 * @Date 2017-08-18 19:54:41
 *
 */
@Repository
public class ProxyContractImpl extends AbstractBaseContract<ProxyEntity, ProxyMapper> implements IProxyContract {

	@Override
	public int addIncome(long userid, int amount, int times, int dividedAmount) throws Exception {
		return mapper.updateByStatement("trade_num=trade_num+1,trade_amount=trade_amount+" + amount + ",times=times+"
				+ times + ",divided_amount=divided_amount+" + dividedAmount, "userid=" + userid);
	}

	public int deleteProxy(long id) throws Exception {
		return mapper.updateByStatement("status=-9", "id=" + id);
	}

	@Override
	public int updateProxyStatus(long userid, int status) throws Exception {
		return mapper.updateByStatement("status=" + status, "userid=" + userid);
	}

	@Override
	public ProxyEntity findByUserid(long userid) throws Exception {
		return mapper.findByProperty("userid", userid);
	}

	public int addProxyUser(long userid) throws Exception {
		return mapper.updateByStatement("invitation=invitation+1", "userid=" + userid);
	}

	public int addProxyTalentVip(long userid) throws Exception {
		return mapper.updateByStatement("talent_vip_num=talent_vip_num+1", "userid=" + userid);
	}

	@Override
	public int proxyWithdram(long id, int amount) throws Exception {
		return mapper.updateByStatement("settlement_amount=settlement_amount+" + amount, "id=" + id
				+ " and settlement_amount+" + amount + ">0 and settlement_amount+" + amount + "<divided_amount");
	}

}

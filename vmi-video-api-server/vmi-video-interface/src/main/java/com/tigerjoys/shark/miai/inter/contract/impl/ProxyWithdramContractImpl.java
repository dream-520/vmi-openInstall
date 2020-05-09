package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IProxyWithdramContract;
import com.tigerjoys.shark.miai.inter.entity.ProxyWithdramEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ProxyWithdramMapper;

/**
 * 数据库中  代理人提现管理[t_proxy_withdram]表 接口实现类
 * @author yangjunming
 * @Date 2017-09-18 10:44:37
 *
 */
@Repository
public class ProxyWithdramContractImpl extends AbstractBaseContract<ProxyWithdramEntity , ProxyWithdramMapper> implements IProxyWithdramContract {
	
}

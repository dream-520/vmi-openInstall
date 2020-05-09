package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IProxyTransContract;
import com.tigerjoys.shark.miai.inter.entity.ProxyTransEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ProxyTransMapper;

/**
 * 数据库中  [t_proxy_trans]表 接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:54:41
 *
 */
@Repository
public class ProxyTransContractImpl extends AbstractBaseContract<ProxyTransEntity , ProxyTransMapper> implements IProxyTransContract {
	
}

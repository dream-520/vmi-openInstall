package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ProxyWithdramEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  代理人提现管理[t_proxy_withdram]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-09-18 10:44:37
 *
 */
@Producer(entityType=ProxyWithdramEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ProxyWithdramMapper extends BaseMapper<ProxyWithdramEntity> {
    
}
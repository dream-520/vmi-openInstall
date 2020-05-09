package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ProxyTransEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_proxy_trans]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:54:41
 *
 */
@Producer(entityType=ProxyTransEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ProxyTransMapper extends BaseMapper<ProxyTransEntity> {
    
}
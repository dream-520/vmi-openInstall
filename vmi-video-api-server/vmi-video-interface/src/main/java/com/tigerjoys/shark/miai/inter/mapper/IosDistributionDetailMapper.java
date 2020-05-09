package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.IosDistributionDetailEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  IOS分发表明细[t_ios_distribution_detail]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-04-02 15:15:02
 *
 */
@Producer(entityType=IosDistributionDetailEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface IosDistributionDetailMapper extends BaseMapper<IosDistributionDetailEntity> {
    
}
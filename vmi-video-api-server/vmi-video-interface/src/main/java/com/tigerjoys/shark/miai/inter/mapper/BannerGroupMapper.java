package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.BannerGroupEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  banner推荐组[t_banner_group]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-05-17 11:09:55
 *
 */
@Producer(entityType=BannerGroupEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface BannerGroupMapper extends BaseMapper<BannerGroupEntity> {
    
}
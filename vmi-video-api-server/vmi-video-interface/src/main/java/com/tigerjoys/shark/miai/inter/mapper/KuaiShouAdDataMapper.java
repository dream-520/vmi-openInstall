package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.KuaiShouAdDataEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  快手下发的广告数据[t_kuai_shou_ad_data]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-10-06 17:35:46
 *
 */
@Producer(entityType=KuaiShouAdDataEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface KuaiShouAdDataMapper extends BaseMapper<KuaiShouAdDataEntity> {
    
}
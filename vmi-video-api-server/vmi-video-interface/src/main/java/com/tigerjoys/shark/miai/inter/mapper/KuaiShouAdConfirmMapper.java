package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.KuaiShouAdConfirmEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_kuai_shou_ad_confirm]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-10-07 16:51:03
 *
 */
@Producer(entityType=KuaiShouAdConfirmEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface KuaiShouAdConfirmMapper extends BaseMapper<KuaiShouAdConfirmEntity> {
    
}
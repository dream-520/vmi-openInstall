package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ShareVipCardEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_share_vip_card]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2018-05-25 10:26:31
 *
 */
@Producer(entityType=ShareVipCardEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ShareVipCardMapper extends BaseMapper<ShareVipCardEntity> {
    
}
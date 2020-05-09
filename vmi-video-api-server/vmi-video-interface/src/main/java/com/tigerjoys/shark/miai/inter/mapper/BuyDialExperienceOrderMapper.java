package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.BuyDialExperienceOrderEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  购买畅聊订单[t_buy_dial_experience_order]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-11-29 15:30:47
 *
 */
@Producer(entityType=BuyDialExperienceOrderEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface BuyDialExperienceOrderMapper extends BaseMapper<BuyDialExperienceOrderEntity> {
    
}
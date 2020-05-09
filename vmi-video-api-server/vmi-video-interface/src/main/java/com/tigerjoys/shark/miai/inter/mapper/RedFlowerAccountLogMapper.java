package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  小红花账户流水[t_red_flower_account_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-08-09 16:26:44
 *
 */
@Producer(entityType=RedFlowerAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface RedFlowerAccountLogMapper extends BaseMapper<RedFlowerAccountLogEntity> {
    
}
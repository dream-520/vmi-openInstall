package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CopyUserPayActionEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户支付记录[t_copy_user_pay_action]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-12-17 20:19:28
 *
 */
@Producer(entityType=CopyUserPayActionEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface CopyUserPayActionMapper extends BaseMapper<CopyUserPayActionEntity> {
    
}
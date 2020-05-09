package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.PayUserRecordEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  付费用户表[t_pay_user_record]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-10-17 10:29:50
 *
 */
@Producer(entityType=PayUserRecordEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface PayUserRecordMapper extends BaseMapper<PayUserRecordEntity> {
    
}
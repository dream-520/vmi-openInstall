package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CreditScoreConfigureEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  信用分购买配置信息[t_credit_score_configure]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2017-08-16 16:20:39
 *
 */
@Producer(entityType=CreditScoreConfigureEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CreditScoreConfigureMapper extends BaseMapper<CreditScoreConfigureEntity> {
    
}
package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserCreditScoreLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户信用分账户流水[t_user_credit_score_log]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2017-08-18 19:52:17
 *
 */
@Producer(entityType=UserCreditScoreLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserCreditScoreLogMapper extends BaseMapper<UserCreditScoreLogEntity> {
    
}
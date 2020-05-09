package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户收益流水[t_user_income_account_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
@Producer(entityType=UserIncomeAccountLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserIncomeAccountLogMapper extends BaseMapper<UserIncomeAccountLogEntity> {
    
	@Select("select sum(amount)/100 from t_user_income_account_log where service_id=#{userid} and io=1 and type in(24,18) and create_time>=#{start} and create_time<#{end}")
	public Double getAnchorOneDayIncome(@Param("userid") long userid, @Param("start") String start, @Param("end") String end);
}
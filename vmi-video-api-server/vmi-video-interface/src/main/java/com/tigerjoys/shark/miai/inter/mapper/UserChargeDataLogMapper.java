package com.tigerjoys.shark.miai.inter.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserChargeDataLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户充值数据流水表[t_user_charge_data_log]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2018-01-26 08:21:25
 *
 */
@Producer(entityType=UserChargeDataLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserChargeDataLogMapper extends BaseMapper<UserChargeDataLogEntity> {
	
	/**
	 * 获得指定日期-至今的累计充值金额
	 * @param date - Date
	 * @return Long
	 */
	@Select("select sum(amount) from t_user_charge_data_log where userid=#{userid} and create_time>=#{date}")
	public Long getSumAmount(@Param("userid") long userid , @Param("date") Date date);
    
}
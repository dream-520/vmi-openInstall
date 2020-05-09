package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库 用户支付记录[t_user_pay_action]表 dao通用操作接口实现类
 * 
 * @author mouzhanpeng
 * @Date 2017-08-14 16:23:30
 *
 */
@Producer(entityType = UserPayActionEntity.class, providerType = DefaultSqlProvider.class, increment = false)
@Mapper
public interface UserPayActionMapper extends BaseMapper<UserPayActionEntity> {

	@Insert("INSERT INTO t_user_pay_action (`id`,`create_time`,`update_time`,`user_id`,`nickname`,`mobile`,`app_type`,`app_version`,`subject`,`description`,`order_id`,`money`,`trade_no`,`trade_status`,`pay_time`,`buyer_id`,`buyer_email`,`status`,`pay_channel`,`type`,`iap_receipt_md5`)"
			+ "VALUES (#{id},#{create_time},#{update_time},#{user_id},#{nickname},#{mobile},#{app_type},#{app_version},#{subject},#{description},#{order_id},#{money},#{trade_no},#{trade_status},#{pay_time},#{buyer_id},#{buyer_email},#{status},#{pay_channel},#{type},#{iap_receipt_md5})"
			+ "ON DUPLICATE KEY UPDATE user_id=VALUES(user_id),nickname=VALUES(nickname),mobile=VALUES(mobile),app_type=VALUES(app_type),app_version=VALUES(app_version),pay_channel=VALUES(pay_channel),iap_receipt_md5=VALUES(iap_receipt_md5);")
	public abstract void insertOnDuplicate(UserPayActionEntity t);
	
	@Select("select sum(money) from t_user_pay_action where user_id=#{userid} and status=1;")
	public int userRechargeMoney(@Param("userid") long userid);
	
}
package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户每日首次登录日志表[t_first_login_log] 表对应的实体类
 * @author liuman
 * @Date 2017-06-07 13:59:40
 *
 */
@Table(name="t_first_login_log")
public class FirstLoginLogEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID")
	private Long id;
	
	/**
	 * 用户id和今天日期的组合
	 */
	@Column(name="userid_perday",nullable=false,jdbcType=JdbcType.VARCHAR,comment="用户id和今天日期的组合")
	private String userid_perday;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserid_perday() {
		return userid_perday;
	}

	public void setUserid_perday(String userid_perday) {
		this.userid_perday = userid_perday;
	}
	
}
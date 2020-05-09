package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_robot_login_log] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Table(name="t_v_robot_login_log")
public class VRobotLoginLogEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标示
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标示")
	private Long id;
	
	/**
	 * 当前用户
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="当前用户")
	private Long userid;
	
	/**
	 * 登录时间
	 */
	@Column(name="login",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="登录时间")
	private Date login;
	
	/**
	 * 消耗时间
	 */
	@Column(name="consume",nullable=false,jdbcType=JdbcType.BIGINT,comment="消耗时间")
	private Long consume;
	
	/**
	 * 代理ip
	 */
	@Column(name="ip",nullable=true,jdbcType=JdbcType.VARCHAR,comment="代理ip")
	private String ip;
	
	/**
	 * 是否出现了错误
	 */
	@Column(name="error",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否出现了错误")
	private Integer error;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
	}
	
	public Long getConsume() {
		return consume;
	}

	public void setConsume(Long consume) {
		this.consume = consume;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}
	
}
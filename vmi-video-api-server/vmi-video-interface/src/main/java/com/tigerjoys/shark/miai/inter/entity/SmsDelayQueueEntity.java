package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  短信发送延迟队列表[t_sms_delay_queue] 表对应的实体类
 * @author chengang
 * @Date 2017-11-10 10:48:45
 *
 */
@Table(name="t_sms_delay_queue")
public class SmsDelayQueueEntity extends BaseEntity implements Serializable {

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
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * sms发送类型
	 */
	@Column(name="sms_type",nullable=false,jdbcType=JdbcType.SMALLINT,comment="sms发送类型")
	private Integer sms_type;
	
	/**
	 * 发送的JSON参数
	 */
	@Column(name="params",nullable=false,jdbcType=JdbcType.VARCHAR,comment="发送的JSON参数")
	private String params;
	
	/**
	 * 延时发送时间
	 */
	@Column(name="dealy_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="延时发送时间")
	private Date dealy_time;
	
	/**
	 * 手机号码集合
	 */
	@Column(name="mobile",nullable=false,jdbcType=JdbcType.LONGVARCHAR,comment="手机号码集合")
	private String mobile;
	
	/**
	 * 错误次数
	 */
	@Column(name="errors",nullable=false,jdbcType=JdbcType.TINYINT,comment="错误次数")
	private Integer errors;
	
	/**
	 * 错误描述
	 */
	@Column(name="errors_msg",nullable=true,jdbcType=JdbcType.VARCHAR,comment="错误描述")
	private String errors_msg;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getSms_type() {
		return sms_type;
	}

	public void setSms_type(Integer sms_type) {
		this.sms_type = sms_type;
	}
	
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public Date getDealy_time() {
		return dealy_time;
	}

	public void setDealy_time(Date dealy_time) {
		this.dealy_time = dealy_time;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getErrors() {
		return errors;
	}

	public void setErrors(Integer errors) {
		this.errors = errors;
	}
	
	public String getErrors_msg() {
		return errors_msg;
	}

	public void setErrors_msg(String errors_msg) {
		this.errors_msg = errors_msg;
	}
	
}
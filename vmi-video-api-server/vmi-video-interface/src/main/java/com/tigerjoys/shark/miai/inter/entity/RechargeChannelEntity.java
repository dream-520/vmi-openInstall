package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  充值渠道列表[t_recharge_channel] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2017-05-12 11:08:02
 *
 */
@Table(name="t_recharge_channel")
public class RechargeChannelEntity extends BaseEntity implements Serializable {

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
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 渠道名
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="渠道名")
	private String name;
	
	/**
	 * 渠道客户端展示名
	 */
	@Column(name="show_name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="渠道客户端展示名")
	private String show_name;
	
	/**
	 * 唯一标识(1,2,3...)
	 */
	@Column(name="code",nullable=false,jdbcType=JdbcType.INTEGER,comment="唯一标识(1,2,3...)")
	private Integer code;
	
	/**
	 * 1=正常;0=停用;
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1=正常;0=停用;")
	private Integer status;
	
	/**
	 * 1=默认;0=非默认;
	 */
	@Column(name="power",nullable=false,jdbcType=JdbcType.TINYINT,comment="1=默认;0=非默认;")
	private Integer power;
	
	/**
	 * 折扣（%）
	 */
	@Column(name="discount",nullable=false,jdbcType=JdbcType.SMALLINT,comment="折扣（%）")
	private Integer discount;
	
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getShow_name() {
		return show_name;
	}

	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
	
	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
}
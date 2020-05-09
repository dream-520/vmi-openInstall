package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_share_vip_card] 表对应的实体类
 * @author yangjunming
 * @Date 2018-05-25 10:26:31
 *
 */
@Table(name="t_share_vip_card")
public class ShareVipCardEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * mobile
	 */
	@Column(name="mobile",nullable=false,jdbcType=JdbcType.VARCHAR,comment="mobile")
	private String mobile;
	
	/**
	 * status
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="status")
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
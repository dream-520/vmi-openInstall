package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户禁用充值入口[t_forbidden_recharge] 表对应的实体类
 * @author lipeng
 * @Date 2020-03-14 15:08:39
 *
 */
@Table(name="t_forbidden_recharge")
public class ForbiddenRechargeEntity extends BaseEntity implements Serializable {

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
	 * 用户IDe
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户IDe")
	private Long userid;
	
	/**
	 * 禁用内容
	 */
	@Column(name="forbidden_text",nullable=false,jdbcType=JdbcType.VARCHAR,comment="禁用内容")
	private String forbidden_text;
	
	/**
	 * 状态
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态")
	private Integer status;
	
	/**
	 * 备注
	 */
	@Column(name="memo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String memo;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
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
	
	public String getForbidden_text() {
		return forbidden_text;
	}

	public void setForbidden_text(String forbidden_text) {
		this.forbidden_text = forbidden_text;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
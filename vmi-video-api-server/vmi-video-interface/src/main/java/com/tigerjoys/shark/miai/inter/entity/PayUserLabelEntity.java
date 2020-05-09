package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  付费用户标签表[t_pay_user_label] 表对应的实体类
 * @author lipeng
 * @Date 2019-11-30 15:58:25
 *
 */
@Table(name="t_pay_user_label")
public class PayUserLabelEntity extends BaseEntity implements Serializable {

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
	 * 更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 标签
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标签")
	private String name;
	
	/**
	 * 价格提升比例
	 */
	@Column(name="ratio",nullable=false,jdbcType=JdbcType.INTEGER,comment="价格提升比例")
	private Integer ratio;
	
	/**
	 * 说明
	 */
	@Column(name="detail",nullable=false,jdbcType=JdbcType.VARCHAR,comment="说明")
	private String detail;
	
	/**
	 * 备注
	 */
	@Column(name="memo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String memo;
	
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
	
	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
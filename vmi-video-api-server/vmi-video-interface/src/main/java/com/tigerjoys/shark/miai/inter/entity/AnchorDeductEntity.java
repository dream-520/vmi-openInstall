package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_anchor_deduct] 表对应的实体类
 * @author shiming
 * @Date 2019-09-20 19:28:15
 *
 */
@Table(name="t_anchor_deduct")
public class AnchorDeductEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标识
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标识")
	private Long id;
	
	/**
	 * 用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 起始点
	 */
	@Column(name="time_start",nullable=true,jdbcType=JdbcType.INTEGER,comment="起始点")
	private Integer time_start;
	
	/**
	 * 结束点
	 */
	@Column(name="time_end",nullable=true,jdbcType=JdbcType.INTEGER,comment="结束点")
	private Integer time_end;
	
	/**
	 * 抵扣比例值
	 */
	@Column(name="deduct",nullable=true,jdbcType=JdbcType.FLOAT,comment="抵扣比例值")
	private Float deduct;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
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
	
	public Integer getTime_start() {
		return time_start;
	}

	public void setTime_start(Integer time_start) {
		this.time_start = time_start;
	}
	
	public Integer getTime_end() {
		return time_end;
	}

	public void setTime_end(Integer time_end) {
		this.time_end = time_end;
	}
	
	public Float getDeduct() {
		return deduct;
	}

	public void setDeduct(Float deduct) {
		this.deduct = deduct;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
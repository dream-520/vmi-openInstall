package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  付费用户活跃表[t_statistics_pay_act_user] 表对应的实体类
 * @author lipeng
 * @Date 2019-11-27 19:56:54
 *
 */
@Table(name="t_statistics_pay_act_user")
public class StatisticsPayActUserEntity extends BaseEntity implements Serializable {

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
	 * 新增或者累计数据生成的时间
	 */
	@Column(name="date",nullable=false,jdbcType=JdbcType.DATE,comment="新增或者累计数据生成的时间")
	private Date date;
	
	/**
	 * 活跃数
	 */
	@Column(name="act_count",nullable=false,jdbcType=JdbcType.BIGINT,comment="活跃数")
	private Long act_count;
	
	/**
	 * 付费人数
	 */
	@Column(name="pay_ples",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费人数")
	private Long pay_ples;
	
	/**
	 * 付费笔数
	 */
	@Column(name="pay_stroke",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费笔数")
	private Long pay_stroke;
	
	/**
	 * 付费金额
	 */
	@Column(name="pay_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费金额")
	private Long pay_total;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Long getAct_count() {
		return act_count;
	}

	public void setAct_count(Long act_count) {
		this.act_count = act_count;
	}
	
	public Long getPay_ples() {
		return pay_ples;
	}

	public void setPay_ples(Long pay_ples) {
		this.pay_ples = pay_ples;
	}
	
	public Long getPay_stroke() {
		return pay_stroke;
	}

	public void setPay_stroke(Long pay_stroke) {
		this.pay_stroke = pay_stroke;
	}
	
	public Long getPay_total() {
		return pay_total;
	}

	public void setPay_total(Long pay_total) {
		this.pay_total = pay_total;
	}
	
}
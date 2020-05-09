package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  蜜聊CPS每日收益统计[t_statistics_cps_income_daily] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-24 20:33:50
 *
 */
@Table(name="t_statistics_cps_income_daily")
public class StatisticsCpsIncomeDailyEntity extends BaseEntity implements Serializable {

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
	 * 核查日期
	 */
	@Column(name="check_date",nullable=true,jdbcType=JdbcType.DATE,comment="核查日期")
	private Date check_date;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * A级收益
	 */
	@Column(name="a_income",nullable=true,jdbcType=JdbcType.BIGINT,comment="A级收益")
	private Long a_income;
	
	/**
	 * B级收益
	 */
	@Column(name="b_income",nullable=true,jdbcType=JdbcType.BIGINT,comment="B级收益")
	private Long b_income;
	
	/**
	 * C级收益
	 */
	@Column(name="c_income",nullable=true,jdbcType=JdbcType.BIGINT,comment="C级收益")
	private Long c_income;
	
	/**
	 * 总收益
	 */
	@Column(name="total_income",nullable=true,jdbcType=JdbcType.BIGINT,comment="总收益")
	private Long total_income;
	
	/**
	 * 包名
	 */
	@Column(name="package_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
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
	
	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getA_income() {
		return a_income;
	}

	public void setA_income(Long a_income) {
		this.a_income = a_income;
	}
	
	public Long getB_income() {
		return b_income;
	}

	public void setB_income(Long b_income) {
		this.b_income = b_income;
	}
	
	public Long getC_income() {
		return c_income;
	}

	public void setC_income(Long c_income) {
		this.c_income = c_income;
	}
	
	public Long getTotal_income() {
		return total_income;
	}

	public void setTotal_income(Long total_income) {
		this.total_income = total_income;
	}
	
	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
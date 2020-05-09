package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_user_invite_cps_statistics] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-17 11:23:55
 *
 */
@Table(name="t_user_invite_cps_statistics")
public class UserInviteCpsStatisticsEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * A级用户数
	 */
	@Column(name="a_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="A级用户数")
	private Integer a_num;
	
	/**
	 * B级用户数
	 */
	@Column(name="b_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="B级用户数")
	private Integer b_num;
	
	/**
	 * C级用户数
	 */
	@Column(name="c_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="C级用户数")
	private Integer c_num;
	
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
	 * 包名
	 */
	@Column(name="package_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
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
	
	public Integer getA_num() {
		return a_num;
	}

	public void setA_num(Integer a_num) {
		this.a_num = a_num;
	}
	
	public Integer getB_num() {
		return b_num;
	}

	public void setB_num(Integer b_num) {
		this.b_num = b_num;
	}
	
	public Integer getC_num() {
		return c_num;
	}

	public void setC_num(Integer c_num) {
		this.c_num = c_num;
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}
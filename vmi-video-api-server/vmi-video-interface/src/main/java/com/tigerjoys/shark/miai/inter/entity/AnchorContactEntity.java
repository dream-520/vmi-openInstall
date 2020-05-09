package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播对应的联系方式信息[t_anchor_contact] 表对应的实体类
 * @author shiming
 * @Date 2020-01-04 19:55:17
 *
 */
@Table(name="t_anchor_contact")
public class AnchorContactEntity extends BaseEntity implements Serializable {

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
	 * 用户id标识
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id标识")
	private Long userid;
	
	/**
	 * 手机号
	 */
	@Column(name="mobile",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机号")
	private String mobile;
	
	/**
	 * 手机号开关
	 */
	@Column(name="mobile_on",nullable=true,jdbcType=JdbcType.TINYINT,comment="手机号开关")
	private Integer mobile_on;
	
	/**
	 * 手机号价格
	 */
	@Column(name="mobile_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="手机号价格")
	private Integer mobile_price;
	
	/**
	 * 微信号
	 */
	@Column(name="weixin",nullable=true,jdbcType=JdbcType.VARCHAR,comment="微信号")
	private String weixin;
	
	/**
	 * 微信号开关
	 */
	@Column(name="weixin_on",nullable=true,jdbcType=JdbcType.TINYINT,comment="微信号开关")
	private Integer weixin_on;
	
	/**
	 * 微信号价格
	 */
	@Column(name="weixin_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="微信号价格")
	private Integer weixin_price;
	
	/**
	 * qq号
	 */
	@Column(name="qq",nullable=true,jdbcType=JdbcType.VARCHAR,comment="qq号")
	private String qq;
	
	/**
	 * qq号开关
	 */
	@Column(name="qq_on",nullable=true,jdbcType=JdbcType.TINYINT,comment="qq号开关")
	private Integer qq_on;
	
	/**
	 * qq号价格
	 */
	@Column(name="qq_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="qq号价格")
	private Integer qq_price;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
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
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getMobile_on() {
		return mobile_on;
	}

	public void setMobile_on(Integer mobile_on) {
		this.mobile_on = mobile_on;
	}
	
	public Integer getMobile_price() {
		return mobile_price;
	}

	public void setMobile_price(Integer mobile_price) {
		this.mobile_price = mobile_price;
	}
	
	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	
	public Integer getWeixin_on() {
		return weixin_on;
	}

	public void setWeixin_on(Integer weixin_on) {
		this.weixin_on = weixin_on;
	}
	
	public Integer getWeixin_price() {
		return weixin_price;
	}

	public void setWeixin_price(Integer weixin_price) {
		this.weixin_price = weixin_price;
	}
	
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public Integer getQq_on() {
		return qq_on;
	}

	public void setQq_on(Integer qq_on) {
		this.qq_on = qq_on;
	}
	
	public Integer getQq_price() {
		return qq_price;
	}

	public void setQq_price(Integer qq_price) {
		this.qq_price = qq_price;
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
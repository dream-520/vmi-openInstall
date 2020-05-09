package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  代理人收益明细[t_proxy_trans] 表对应的实体类
 * @author yangjunming
 * @Date 2017-11-06 17:39:42
 *
 */
@Table(name="t_proxy_trans")
public class ProxyTransEntity extends BaseEntity implements Serializable {

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
	 * 邀请者id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="邀请者id")
	private Long userid;
	
	/**
	 * 被邀请者ID
	 */
	@Column(name="incomeid",nullable=true,jdbcType=JdbcType.BIGINT,comment="被邀请者ID")
	private Long incomeid;
	
	/**
	 * 分享分类id
	 */
	@Column(name="mappingid",nullable=true,jdbcType=JdbcType.BIGINT,comment="分享分类id")
	private Long mappingid;
	
	/**
	 * 订单号
	 */
	@Column(name="orderid",nullable=true,jdbcType=JdbcType.BIGINT,comment="订单号")
	private Long orderid;
	
	/**
	 * 订单种类
	 */
	@Column(name="typeid",nullable=true,jdbcType=JdbcType.INTEGER,comment="订单种类")
	private Integer typeid;
	
	/**
	 * 交易金额
	 */
	@Column(name="trade_amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="交易金额")
	private Integer trade_amount;
	
	/**
	 * 时长
	 */
	@Column(name="times",nullable=true,jdbcType=JdbcType.INTEGER,comment="时长")
	private Integer times;
	
	/**
	 * 分成
	 */
	@Column(name="divided_amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="分成")
	private Integer divided_amount;
	
	/**
	 * 状态 1 收入 2 退款
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态 1 收入 2 退款")
	private Integer status;
	
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
	
	public Long getIncomeid() {
		return incomeid;
	}

	public void setIncomeid(Long incomeid) {
		this.incomeid = incomeid;
	}
	
	public Long getMappingid() {
		return mappingid;
	}

	public void setMappingid(Long mappingid) {
		this.mappingid = mappingid;
	}
	
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	
	public Integer getTrade_amount() {
		return trade_amount;
	}

	public void setTrade_amount(Integer trade_amount) {
		this.trade_amount = trade_amount;
	}
	
	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
	public Integer getDivided_amount() {
		return divided_amount;
	}

	public void setDivided_amount(Integer divided_amount) {
		this.divided_amount = divided_amount;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
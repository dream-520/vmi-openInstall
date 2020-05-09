package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  代理人表[t_proxy] 表对应的实体类
 * @author yangjunming
 * @Date 2017-11-30 11:13:36
 *
 */
@Table(name="t_proxy")
public class ProxyEntity extends BaseEntity implements Serializable {

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
	 * 贷理申请入口 0 线下 1线上 
	 */
	@Column(name="inlet",nullable=true,jdbcType=JdbcType.TINYINT,comment="贷理申请入口 0 线下 1线上 ")
	private Integer inlet;
	
	/**
	 * 邀请人数
	 */
	@Column(name="invitation",nullable=true,jdbcType=JdbcType.INTEGER,comment="邀请人数")
	private Integer invitation;
	
	/**
	 * 达人VIP人数
	 */
	@Column(name="talent_vip_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="达人VIP人数")
	private Integer talent_vip_num;
	
	/**
	 * 总成交金额
	 */
	@Column(name="trade_amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="总成交金额")
	private Integer trade_amount;
	
	/**
	 * 总时长
	 */
	@Column(name="times",nullable=true,jdbcType=JdbcType.BIGINT,comment="总时长")
	private Long times;
	
	/**
	 * 总笔数
	 */
	@Column(name="trade_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="总笔数")
	private Integer trade_num;
	
	/**
	 * 总分成
	 */
	@Column(name="divided_amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="总分成")
	private Integer divided_amount;
	
	/**
	 * 已结算
	 */
	@Column(name="settlement_amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="已结算")
	private Integer settlement_amount;
	
	/**
	 * 状态 0 解除 1  恢复
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态 0 解除 1  恢复")
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
	
	public Integer getInlet() {
		return inlet;
	}

	public void setInlet(Integer inlet) {
		this.inlet = inlet;
	}
	
	public Integer getInvitation() {
		return invitation;
	}

	public void setInvitation(Integer invitation) {
		this.invitation = invitation;
	}
	
	public Integer getTalent_vip_num() {
		return talent_vip_num;
	}

	public void setTalent_vip_num(Integer talent_vip_num) {
		this.talent_vip_num = talent_vip_num;
	}
	
	public Integer getTrade_amount() {
		return trade_amount;
	}

	public void setTrade_amount(Integer trade_amount) {
		this.trade_amount = trade_amount;
	}
	
	public Long getTimes() {
		return times;
	}

	public void setTimes(Long times) {
		this.times = times;
	}
	
	public Integer getTrade_num() {
		return trade_num;
	}

	public void setTrade_num(Integer trade_num) {
		this.trade_num = trade_num;
	}
	
	public Integer getDivided_amount() {
		return divided_amount;
	}

	public void setDivided_amount(Integer divided_amount) {
		this.divided_amount = divided_amount;
	}
	
	public Integer getSettlement_amount() {
		return settlement_amount;
	}

	public void setSettlement_amount(Integer settlement_amount) {
		this.settlement_amount = settlement_amount;
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
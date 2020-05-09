package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户达人收入统计[t_user_talent_vip_statistics] 表对应的实体类
 * @author yangjunming
 * @Date 2017-11-27 11:31:09
 *
 */
@Table(name="t_user_talent_vip_statistics")
public class UserTalentVipStatisticsEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 交易笔数
	 */
	@Column(name="trade_num",nullable=false,jdbcType=JdbcType.INTEGER,comment="交易笔数")
	private Integer trade_num;
	
	/**
	 * 交易总金额
	 */
	@Column(name="trade_amount",nullable=false,jdbcType=JdbcType.INTEGER,comment="交易总金额")
	private Integer trade_amount;
	
	/**
	 * create_time
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="create_time")
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
	
	public Integer getTrade_num() {
		return trade_num;
	}

	public void setTrade_num(Integer trade_num) {
		this.trade_num = trade_num;
	}
	
	public Integer getTrade_amount() {
		return trade_amount;
	}

	public void setTrade_amount(Integer trade_amount) {
		this.trade_amount = trade_amount;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
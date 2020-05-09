package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户VIP充值记录[t_vip_log] 表对应的实体类
 * @author yangjunming
 * @Date 2018-08-22 15:16:46
 *
 */
@Table(name="t_vip_log")
public class VipLogEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * vip类别ID
	 */
	@Column(name="category_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="vip类别ID")
	private Long category_id;
	
	/**
	 * 支付类型 1 钻石 2 能量
	 */
	@Column(name="pay_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="支付类型 1 钻石 2 能量")
	private Integer pay_type;
	
	/**
	 * 钻石
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.BIGINT,comment="钻石")
	private Long diamond;
	
	/**
	 * 延长月数
	 */
	@Column(name="months",nullable=false,jdbcType=JdbcType.INTEGER,comment="延长月数")
	private Integer months;
	
	/**
	 * 交易流水标识
	 */
	@Column(name="transaction_flow",nullable=false,jdbcType=JdbcType.VARCHAR,comment="交易流水标识")
	private String transaction_flow;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	
	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	
	public Long getDiamond() {
		return diamond;
	}

	public void setDiamond(Long diamond) {
		this.diamond = diamond;
	}
	
	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}
	
	public String getTransaction_flow() {
		return transaction_flow;
	}

	public void setTransaction_flow(String transaction_flow) {
		this.transaction_flow = transaction_flow;
	}
	
}
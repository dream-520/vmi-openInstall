package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户充值行为分析表[t_statistics_user_pay_analysis] 表对应的实体类
 * @author shiming
 * @Date 2019-07-02 14:31:06
 *
 */
@Table(name="t_statistics_user_pay_analysis")
public class StatisticsUserPayAnalysisEntity extends BaseEntity implements Serializable {

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
	 * 审查时间
	 */
	@Column(name="check_date",nullable=true,jdbcType=JdbcType.DATE,comment="审查时间")
	private Date check_date;
	
	/**
	 * 主播id
	 */
	@Column(name="anchorid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播id")
	private Long anchorid;
	
	/**
	 * 用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * orderid
	 */
	@Column(name="orderid",nullable=true,jdbcType=JdbcType.BIGINT,comment="orderid")
	private Long orderid;
	
	/**
	 * 用户对应的充值钱数
	 */
	@Column(name="money",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户对应的充值钱数")
	private Integer money;
	
	/**
	 * 用于表示是否是用户主动充值  0 用户主动充值  1 主播引诱充值
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="用于表示是否是用户主动充值  0 用户主动充值  1 主播引诱充值")
	private Integer type;
	
	/**
	 * 1 音频  2 视频
	 */
	@Column(name="av_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="1 音频  2 视频")
	private Integer av_type;
	
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
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getAv_type() {
		return av_type;
	}

	public void setAv_type(Integer av_type) {
		this.av_type = av_type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
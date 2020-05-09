package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播等级考核表[t_anchor_level_check] 表对应的实体类
 * @author yangjunming
 * @Date 2019-08-30 16:13:01
 *
 */
@Table(name="t_anchor_level_check")
public class AnchorLevelCheckEntity extends BaseEntity implements Serializable {

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
	 * 主播用户ID
	 */
	@Column(name="anchorId",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播用户ID")
	private Long anchorId;
	
	/**
	 * 在线时长
	 */
	@Column(name="online_time",nullable=true,jdbcType=JdbcType.INTEGER,comment="在线时长")
	private Integer online_time;
	
	/**
	 * 在聊时长
	 */
	@Column(name="total_duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊时长")
	private Integer total_duration;
	
	/**
	 * 付费用户在聊时长
	 */
	@Column(name="pay_total_duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="付费用户在聊时长")
	private Integer pay_total_duration;
	
	/**
	 * 付费用户在聊次数
	 */
	@Column(name="pay_total_rows",nullable=true,jdbcType=JdbcType.INTEGER,comment="付费用户在聊次数")
	private Integer pay_total_rows;
	
	/**
	 * 平均付费用户在聊时长
	 */
	@Column(name="avg_pay_duration",nullable=true,jdbcType=JdbcType.DECIMAL,comment="平均付费用户在聊时长")
	private Double avg_pay_duration;
	
	/**
	 * 拨打次数
	 */
	@Column(name="total_rows",nullable=true,jdbcType=JdbcType.INTEGER,comment="拨打次数")
	private Integer total_rows;
	
	/**
	 * 接听次数
	 */
	@Column(name="total_recv",nullable=true,jdbcType=JdbcType.INTEGER,comment="接听次数")
	private Integer total_recv;
	
	/**
	 * 接听率
	 */
	@Column(name="avg_recv",nullable=true,jdbcType=JdbcType.DECIMAL,comment="接听率")
	private Double avg_recv;
	
	/**
	 * 转化付费人数
	 */
	@Column(name="trans_pay_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费人数")
	private Integer trans_pay_user_num;
	
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
	
	public Long getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Long anchorId) {
		this.anchorId = anchorId;
	}
	
	public Integer getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Integer online_time) {
		this.online_time = online_time;
	}
	
	public Integer getTotal_duration() {
		return total_duration;
	}

	public void setTotal_duration(Integer total_duration) {
		this.total_duration = total_duration;
	}
	
	public Integer getPay_total_duration() {
		return pay_total_duration;
	}

	public void setPay_total_duration(Integer pay_total_duration) {
		this.pay_total_duration = pay_total_duration;
	}
	
	public Integer getPay_total_rows() {
		return pay_total_rows;
	}

	public void setPay_total_rows(Integer pay_total_rows) {
		this.pay_total_rows = pay_total_rows;
	}
	
	public Double getAvg_pay_duration() {
		return avg_pay_duration;
	}

	public void setAvg_pay_duration(Double avg_pay_duration) {
		this.avg_pay_duration = avg_pay_duration;
	}
	
	public Integer getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(Integer total_rows) {
		this.total_rows = total_rows;
	}
	
	public Integer getTotal_recv() {
		return total_recv;
	}

	public void setTotal_recv(Integer total_recv) {
		this.total_recv = total_recv;
	}
	
	public Double getAvg_recv() {
		return avg_recv;
	}

	public void setAvg_recv(Double avg_recv) {
		this.avg_recv = avg_recv;
	}
	
	public Integer getTrans_pay_user_num() {
		return trans_pay_user_num;
	}

	public void setTrans_pay_user_num(Integer trans_pay_user_num) {
		this.trans_pay_user_num = trans_pay_user_num;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
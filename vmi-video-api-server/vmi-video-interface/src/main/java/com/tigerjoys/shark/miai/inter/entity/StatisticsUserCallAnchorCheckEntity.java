package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户拨打主播统计[t_statistics_user_call_anchor_check] 表对应的实体类
 * @author yangjunming
 * @Date 2019-11-02 18:20:53
 *
 */
@Table(name="t_statistics_user_call_anchor_check")
public class StatisticsUserCallAnchorCheckEntity extends BaseEntity implements Serializable {

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
	 * 1 音频  2 视频
	 */
	@Column(name="av_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="1 音频  2 视频")
	private Integer av_type;
	
	/**
	 * 0 新用户 1 新老用户
	 */
	@Column(name="user_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 新用户 1 新老用户")
	private Integer user_type;
	
	/**
	 * 用户打次数
	 */
	@Column(name="user_dial_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户打次数")
	private Integer user_dial_times;
	
	/**
	 * 用户打人数
	 */
	@Column(name="user_dial_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户打人数")
	private Integer user_dial_num;
	
	/**
	 * 用户拨通人数
	 */
	@Column(name="user_dial_user_dial_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户拨通人数")
	private Integer user_dial_user_dial_num;
	
	/**
	 * 主播接通次数
	 */
	@Column(name="user_dial_recv_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播接通次数")
	private Integer user_dial_recv_times;
	
	/**
	 * 用户拨打主播人数
	 */
	@Column(name="user_dial_recv_anchor_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户拨打主播人数")
	private Integer user_dial_recv_anchor_num;
	
	/**
	 * 转化付费次数
	 */
	@Column(name="anchor_trans_user_pay_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费次数")
	private Integer anchor_trans_user_pay_times;
	
	/**
	 * 转化付费金额
	 */
	@Column(name="anchor_trans_user_pay_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费金额")
	private Integer anchor_trans_user_pay_money;
	
	/**
	 * 转化付费人数
	 */
	@Column(name="anchor_trans_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费人数")
	private Integer anchor_trans_user_num;
	
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
	
	public Integer getAv_type() {
		return av_type;
	}

	public void setAv_type(Integer av_type) {
		this.av_type = av_type;
	}
	
	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	
	public Integer getUser_dial_times() {
		return user_dial_times;
	}

	public void setUser_dial_times(Integer user_dial_times) {
		this.user_dial_times = user_dial_times;
	}
	
	public Integer getUser_dial_num() {
		return user_dial_num;
	}

	public void setUser_dial_num(Integer user_dial_num) {
		this.user_dial_num = user_dial_num;
	}
	
	public Integer getUser_dial_user_dial_num() {
		return user_dial_user_dial_num;
	}

	public void setUser_dial_user_dial_num(Integer user_dial_user_dial_num) {
		this.user_dial_user_dial_num = user_dial_user_dial_num;
	}
	
	public Integer getUser_dial_recv_times() {
		return user_dial_recv_times;
	}

	public void setUser_dial_recv_times(Integer user_dial_recv_times) {
		this.user_dial_recv_times = user_dial_recv_times;
	}
	
	public Integer getUser_dial_recv_anchor_num() {
		return user_dial_recv_anchor_num;
	}

	public void setUser_dial_recv_anchor_num(Integer user_dial_recv_anchor_num) {
		this.user_dial_recv_anchor_num = user_dial_recv_anchor_num;
	}
	
	public Integer getAnchor_trans_user_pay_times() {
		return anchor_trans_user_pay_times;
	}

	public void setAnchor_trans_user_pay_times(Integer anchor_trans_user_pay_times) {
		this.anchor_trans_user_pay_times = anchor_trans_user_pay_times;
	}
	
	public Integer getAnchor_trans_user_pay_money() {
		return anchor_trans_user_pay_money;
	}

	public void setAnchor_trans_user_pay_money(Integer anchor_trans_user_pay_money) {
		this.anchor_trans_user_pay_money = anchor_trans_user_pay_money;
	}
	
	public Integer getAnchor_trans_user_num() {
		return anchor_trans_user_num;
	}

	public void setAnchor_trans_user_num(Integer anchor_trans_user_num) {
		this.anchor_trans_user_num = anchor_trans_user_num;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
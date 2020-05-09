package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  工会主播汇总统计[t_statistics_labor_anchor_trans] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-09 19:43:53
 *
 */
@Table(name="t_statistics_labor_anchor_trans")
public class StatisticsLaborAnchorTransEntity extends BaseEntity implements Serializable {

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
	 * 工会ID
	 */
	@Column(name="labor_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="工会ID")
	private Long labor_id;
	
	/**
	 * 1 汇总  0 明细
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="1 汇总  0 明细")
	private Integer type;
	
	/**
	 * 在线时长
	 */
	@Column(name="online_time",nullable=true,jdbcType=JdbcType.INTEGER,comment="在线时长")
	private Integer online_time;
	
	/**
	 * 总拨打接听次数
	 */
	@Column(name="total_dial_recv_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="总拨打接听次数")
	private Integer total_dial_recv_times;
	
	/**
	 * 在聊时长
	 */
	@Column(name="total_duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊时长")
	private Integer total_duration;
	
	/**
	 * 真在聊时长
	 */
	@Column(name="total_real_duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="真在聊时长")
	private Integer total_real_duration;
	
	/**
	 * 单次时长
	 */
	@Column(name="avg_duration",nullable=true,jdbcType=JdbcType.DECIMAL,comment="单次时长")
	private Double avg_duration;
	
	/**
	 * 真单次时长
	 */
	@Column(name="avg_real_duration",nullable=true,jdbcType=JdbcType.DECIMAL,comment="真单次时长")
	private Double avg_real_duration;
	
	/**
	 * 主播拨打数
	 */
	@Column(name="anchor_dial_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播拨打数")
	private Integer anchor_dial_times;
	
	/**
	 * 用户拨打次数
	 */
	@Column(name="user_dial_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户拨打次数")
	private Integer user_dial_times;
	
	/**
	 * 用户拨打接听次数
	 */
	@Column(name="user_dial_recv_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户拨打接听次数")
	private Integer user_dial_recv_times;
	
	/**
	 * 主播接通率
	 */
	@Column(name="avg_anchor_recv",nullable=true,jdbcType=JdbcType.DECIMAL,comment="主播接通率")
	private Double avg_anchor_recv;
	
	/**
	 * 邀请用户量
	 */
	@Column(name="anchor_invite_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="邀请用户量")
	private Integer anchor_invite_num;
	
	/**
	 * 总流水
	 */
	@Column(name="trans_total_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="总流水")
	private Integer trans_total_money;
	
	/**
	 * 真总流水
	 */
	@Column(name="trans_real_total_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="真总流水")
	private Integer trans_real_total_money;
	
	/**
	 * 邀请收益
	 */
	@Column(name="trans_invite_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="邀请收益")
	private Integer trans_invite_money;
	
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
	
	public Long getLabor_id() {
		return labor_id;
	}

	public void setLabor_id(Long labor_id) {
		this.labor_id = labor_id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Integer online_time) {
		this.online_time = online_time;
	}
	
	public Integer getTotal_dial_recv_times() {
		return total_dial_recv_times;
	}

	public void setTotal_dial_recv_times(Integer total_dial_recv_times) {
		this.total_dial_recv_times = total_dial_recv_times;
	}
	
	public Integer getTotal_duration() {
		return total_duration;
	}

	public void setTotal_duration(Integer total_duration) {
		this.total_duration = total_duration;
	}
	
	public Integer getTotal_real_duration() {
		return total_real_duration;
	}

	public void setTotal_real_duration(Integer total_real_duration) {
		this.total_real_duration = total_real_duration;
	}
	
	public Double getAvg_duration() {
		return avg_duration;
	}

	public void setAvg_duration(Double avg_duration) {
		this.avg_duration = avg_duration;
	}
	
	public Double getAvg_real_duration() {
		return avg_real_duration;
	}

	public void setAvg_real_duration(Double avg_real_duration) {
		this.avg_real_duration = avg_real_duration;
	}
	
	public Integer getAnchor_dial_times() {
		return anchor_dial_times;
	}

	public void setAnchor_dial_times(Integer anchor_dial_times) {
		this.anchor_dial_times = anchor_dial_times;
	}
	
	public Integer getUser_dial_times() {
		return user_dial_times;
	}

	public void setUser_dial_times(Integer user_dial_times) {
		this.user_dial_times = user_dial_times;
	}
	
	public Integer getUser_dial_recv_times() {
		return user_dial_recv_times;
	}

	public void setUser_dial_recv_times(Integer user_dial_recv_times) {
		this.user_dial_recv_times = user_dial_recv_times;
	}
	
	public Double getAvg_anchor_recv() {
		return avg_anchor_recv;
	}

	public void setAvg_anchor_recv(Double avg_anchor_recv) {
		this.avg_anchor_recv = avg_anchor_recv;
	}
	
	public Integer getAnchor_invite_num() {
		return anchor_invite_num;
	}

	public void setAnchor_invite_num(Integer anchor_invite_num) {
		this.anchor_invite_num = anchor_invite_num;
	}
	
	public Integer getTrans_total_money() {
		return trans_total_money;
	}

	public void setTrans_total_money(Integer trans_total_money) {
		this.trans_total_money = trans_total_money;
	}
	
	public Integer getTrans_real_total_money() {
		return trans_real_total_money;
	}

	public void setTrans_real_total_money(Integer trans_real_total_money) {
		this.trans_real_total_money = trans_real_total_money;
	}
	
	public Integer getTrans_invite_money() {
		return trans_invite_money;
	}

	public void setTrans_invite_money(Integer trans_invite_money) {
		this.trans_invite_money = trans_invite_money;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
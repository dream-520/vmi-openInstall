package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播主动拨打收益表[t_anchor_income_initiative_check] 表对应的实体类
 * @author yangjunming
 * @Date 2019-11-02 18:20:52
 *
 */
@Table(name="t_anchor_income_initiative_check")
public class AnchorIncomeInitiativeCheckEntity extends BaseEntity implements Serializable {

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
	 * 主播接通次数
	 */
	@Column(name="recv_total",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播接通次数")
	private Integer recv_total;
	
	/**
	 * 主动接通人次
	 */
	@Column(name="recv_people",nullable=true,jdbcType=JdbcType.INTEGER,comment="主动接通人次")
	private Integer recv_people;
	
	/**
	 * 主动拨打次数
	 */
	@Column(name="call_total",nullable=true,jdbcType=JdbcType.INTEGER,comment="主动拨打次数")
	private Integer call_total;
	
	/**
	 * 主动拨打人数
	 */
	@Column(name="call_people",nullable=true,jdbcType=JdbcType.INTEGER,comment="主动拨打人数")
	private Integer call_people;
	
	/**
	 * 在聊总时长
	 */
	@Column(name="total_talking",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊总时长")
	private Integer total_talking;
	
	/**
	 * 总充值次数
	 */
	@Column(name="pay_total",nullable=true,jdbcType=JdbcType.INTEGER,comment="总充值次数")
	private Integer pay_total;
	
	/**
	 * 总充值人数
	 */
	@Column(name="pay_people",nullable=true,jdbcType=JdbcType.INTEGER,comment="总充值人数")
	private Integer pay_people;
	
	/**
	 * 总充值金额
	 */
	@Column(name="pay_sum",nullable=true,jdbcType=JdbcType.INTEGER,comment="总充值金额")
	private Integer pay_sum;
	
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
	
	public Integer getRecv_total() {
		return recv_total;
	}

	public void setRecv_total(Integer recv_total) {
		this.recv_total = recv_total;
	}
	
	public Integer getRecv_people() {
		return recv_people;
	}

	public void setRecv_people(Integer recv_people) {
		this.recv_people = recv_people;
	}
	
	public Integer getCall_total() {
		return call_total;
	}

	public void setCall_total(Integer call_total) {
		this.call_total = call_total;
	}
	
	public Integer getCall_people() {
		return call_people;
	}

	public void setCall_people(Integer call_people) {
		this.call_people = call_people;
	}
	
	public Integer getTotal_talking() {
		return total_talking;
	}

	public void setTotal_talking(Integer total_talking) {
		this.total_talking = total_talking;
	}
	
	public Integer getPay_total() {
		return pay_total;
	}

	public void setPay_total(Integer pay_total) {
		this.pay_total = pay_total;
	}
	
	public Integer getPay_people() {
		return pay_people;
	}

	public void setPay_people(Integer pay_people) {
		this.pay_people = pay_people;
	}
	
	public Integer getPay_sum() {
		return pay_sum;
	}

	public void setPay_sum(Integer pay_sum) {
		this.pay_sum = pay_sum;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播收益考核表[t_anchor_income_check] 表对应的实体类
 * @author lipeng
 * @Date 2019-06-24 16:15:08
 *
 */
@Table(name="t_anchor_income_check")
public class AnchorIncomeCheckEntity extends BaseEntity implements Serializable {

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
	 * 总收益(钻石)
	 */
	@Column(name="total_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="总收益(钻石)")
	private Integer total_price;
	
	/**
	 * 被拨次数
	 */
	@Column(name="total_rows",nullable=true,jdbcType=JdbcType.INTEGER,comment="被拨次数")
	private Integer total_rows;
	
	/**
	 * 接听次数
	 */
	@Column(name="total_recv",nullable=true,jdbcType=JdbcType.INTEGER,comment="接听次数")
	private Integer total_recv;
	
	/**
	 * 下传短视频
	 */
	@Column(name="shortVideo_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="下传短视频")
	private Integer shortVideo_num;
	
	/**
	 * 总收益(元)
	 */
	@Column(name="total_money",nullable=true,jdbcType=JdbcType.DECIMAL,comment="总收益(元)")
	private Double total_money;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
	/**
	 * 拨打人数
	 */
	@Column(name="dial_user",nullable=false,jdbcType=JdbcType.BIGINT,comment="拨打人数")
	private Long dial_user;
	
	/**
	 * 通话人数
	 */
	@Column(name="call_user",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话人数")
	private Long call_user;
	
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
	
	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
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
	
	public Integer getShortVideo_num() {
		return shortVideo_num;
	}

	public void setShortVideo_num(Integer shortVideo_num) {
		this.shortVideo_num = shortVideo_num;
	}
	
	public Double getTotal_money() {
		return total_money;
	}

	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Long getDial_user() {
		return dial_user;
	}

	public void setDial_user(Long dial_user) {
		this.dial_user = dial_user;
	}
	
	public Long getCall_user() {
		return call_user;
	}

	public void setCall_user(Long call_user) {
		this.call_user = call_user;
	}
	
}
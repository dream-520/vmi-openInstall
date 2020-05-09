package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播升级体系[t_anchor_level_manager] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-03 16:13:01
 *
 */
@Table(name="t_anchor_level_manager")
public class AnchorLevelManagerEntity extends BaseEntity implements Serializable {

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
	 * 星级
	 */
	@Column(name="anchor_star",nullable=false,jdbcType=JdbcType.INTEGER,comment="星级")
	private Integer anchor_star;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 核查天数
	 */
	@Column(name="check_days",nullable=true,jdbcType=JdbcType.INTEGER,comment="核查天数")
	private Integer check_days;
	
	/**
	 * 核查成功几天数
	 */
	@Column(name="check_success_days",nullable=true,jdbcType=JdbcType.INTEGER,comment="核查成功几天数")
	private Integer check_success_days;
	
	/**
	 * 核查次数
	 */
	@Column(name="check_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="核查次数")
	private Integer check_times;
	
	/**
	 * 单日平均聊天时长
	 */
	@Column(name="avg_duration",nullable=false,jdbcType=JdbcType.DECIMAL,comment="单日平均聊天时长")
	private Double avg_duration;
	
	/**
	 * 被动接听率
	 */
	@Column(name="avg_recv",nullable=false,jdbcType=JdbcType.DECIMAL,comment="被动接听率")
	private Double avg_recv;
	
	/**
	 * 累计时长
	 */
	@Column(name="total_duration",nullable=false,jdbcType=JdbcType.INTEGER,comment="累计时长")
	private Integer total_duration;
	
	/**
	 * 在线时长
	 */
	@Column(name="online_duration",nullable=false,jdbcType=JdbcType.INTEGER,comment="在线时长")
	private Integer online_duration;
	
	/**
	 * 价格
	 */
	@Column(name="price",nullable=true,jdbcType=JdbcType.INTEGER,comment="价格")
	private Integer price;
	
	/**
	 * 分成比例
	 */
	@Column(name="ratio",nullable=true,jdbcType=JdbcType.FLOAT,comment="分成比例")
	private Float ratio;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getAnchor_star() {
		return anchor_star;
	}

	public void setAnchor_star(Integer anchor_star) {
		this.anchor_star = anchor_star;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Integer getCheck_days() {
		return check_days;
	}

	public void setCheck_days(Integer check_days) {
		this.check_days = check_days;
	}
	
	public Integer getCheck_success_days() {
		return check_success_days;
	}

	public void setCheck_success_days(Integer check_success_days) {
		this.check_success_days = check_success_days;
	}
	
	public Integer getCheck_times() {
		return check_times;
	}

	public void setCheck_times(Integer check_times) {
		this.check_times = check_times;
	}
	
	public Double getAvg_duration() {
		return avg_duration;
	}

	public void setAvg_duration(Double avg_duration) {
		this.avg_duration = avg_duration;
	}
	
	public Double getAvg_recv() {
		return avg_recv;
	}

	public void setAvg_recv(Double avg_recv) {
		this.avg_recv = avg_recv;
	}
	
	public Integer getTotal_duration() {
		return total_duration;
	}

	public void setTotal_duration(Integer total_duration) {
		this.total_duration = total_duration;
	}
	
	public Integer getOnline_duration() {
		return online_duration;
	}

	public void setOnline_duration(Integer online_duration) {
		this.online_duration = online_duration;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Float getRatio() {
		return ratio;
	}

	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}
	
}
package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户每日周卡[t_user_daily_weekcard] 表对应的实体类
 * @author yangjunming
 * @Date 2019-11-16 11:01:30
 *
 */
@Table(name="t_user_daily_weekcard")
public class UserDailyWeekcardEntity extends BaseEntity implements Serializable {

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
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 类型 4绿钻卡 5黄钻卡 
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型 4绿钻卡 5黄钻卡 ")
	private Integer type;
	
	/**
	 * 核查日期
	 */
	@Column(name="check_date",nullable=true,jdbcType=JdbcType.DATE,comment="核查日期")
	private Date check_date;
	
	/**
	 * 当日总分钟数
	 */
	@Column(name="total_duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="当日总分钟数")
	private Integer total_duration;
	
	/**
	 * 当日使用分钟数
	 */
	@Column(name="duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="当日使用分钟数")
	private Integer duration;
	
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	
	public Integer getTotal_duration() {
		return total_duration;
	}

	public void setTotal_duration(Integer total_duration) {
		this.total_duration = total_duration;
	}
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
}
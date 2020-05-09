package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  蜜聊CPS每日邀请统计[t_statistics_cps_share_daily] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-24 20:33:50
 *
 */
@Table(name="t_statistics_cps_share_daily")
public class StatisticsCpsShareDailyEntity extends BaseEntity implements Serializable {

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
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
	/**
	 * 新增用户数
	 */
	@Column(name="newly_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="新增用户数")
	private Integer newly_user_num;
	
	/**
	 * 充值用户数
	 */
	@Column(name="charge_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="充值用户数")
	private Integer charge_user_num;
	
	/**
	 * 充值次数
	 */
	@Column(name="charge_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="充值次数")
	private Integer charge_times;
	
	/**
	 * 充值金额
	 */
	@Column(name="charge_money",nullable=true,jdbcType=JdbcType.BIGINT,comment="充值金额")
	private Long charge_money;
	
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
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Integer getNewly_user_num() {
		return newly_user_num;
	}

	public void setNewly_user_num(Integer newly_user_num) {
		this.newly_user_num = newly_user_num;
	}
	
	public Integer getCharge_user_num() {
		return charge_user_num;
	}

	public void setCharge_user_num(Integer charge_user_num) {
		this.charge_user_num = charge_user_num;
	}
	
	public Integer getCharge_times() {
		return charge_times;
	}

	public void setCharge_times(Integer charge_times) {
		this.charge_times = charge_times;
	}
	
	public Long getCharge_money() {
		return charge_money;
	}

	public void setCharge_money(Long charge_money) {
		this.charge_money = charge_money;
	}
	
}
package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  付费用户表[t_pay_user_record] 表对应的实体类
 * @author lipeng
 * @Date 2019-10-17 10:29:49
 *
 */
@Table(name="t_pay_user_record")
public class PayUserRecordEntity extends BaseEntity implements Serializable {

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
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 注册时间
	 */
	@Column(name="reg_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="注册时间")
	private Date reg_time;
	
	/**
	 * 用户等级
	 */
	@Column(name="user_level",nullable=false,jdbcType=JdbcType.INTEGER,comment="用户等级")
	private Integer user_level;
	
	/**
	 * 首冲时间
	 */
	@Column(name="first_pay_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="首冲时间")
	private Date first_pay_time;
	
	/**
	 * 最近充值时间
	 */
	@Column(name="last_pay_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="最近充值时间")
	private Date last_pay_time;
	
	/**
	 * 最近聊天时间
	 */
	@Column(name="last_chat_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="最近聊天时间")
	private Date last_chat_time;
	
	/**
	 * 最近登录时间
	 */
	@Column(name="last_login_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="最近登录时间")
	private Date last_login_time;
	
	/**
	 * 聊天天数
	 */
	@Column(name="chat_days",nullable=true,jdbcType=JdbcType.INTEGER,comment="聊天天数")
	private Integer chat_days;
	
	/**
	 * 充值次数
	 */
	@Column(name="pay_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="充值次数")
	private Integer pay_times;
	
	/**
	 * 充值金额
	 */
	@Column(name="pay_money",nullable=true,jdbcType=JdbcType.BIGINT,comment="充值金额")
	private Long pay_money;
	
	/**
	 * 剩余钻
	 */
	@Column(name="diamond",nullable=true,jdbcType=JdbcType.BIGINT,comment="剩余钻")
	private Long diamond;
	
	/**
	 * 是否是vip 0：不是
	 */
	@Column(name="vip",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否是vip 0：不是")
	private Integer vip;
	
	/**
	 * 标签
	 */
	@Column(name="labels",nullable=true,jdbcType=JdbcType.VARCHAR,comment="标签")
	private String labels;
	
	/**
	 * 渠道名
	 */
	@Column(name="channel",nullable=false,jdbcType=JdbcType.VARCHAR,comment="渠道名")
	private String channel;
	
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
	
	public Date getReg_time() {
		return reg_time;
	}

	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}
	
	public Integer getUser_level() {
		return user_level;
	}

	public void setUser_level(Integer user_level) {
		this.user_level = user_level;
	}
	
	public Date getFirst_pay_time() {
		return first_pay_time;
	}

	public void setFirst_pay_time(Date first_pay_time) {
		this.first_pay_time = first_pay_time;
	}
	
	public Date getLast_pay_time() {
		return last_pay_time;
	}

	public void setLast_pay_time(Date last_pay_time) {
		this.last_pay_time = last_pay_time;
	}
	
	public Date getLast_chat_time() {
		return last_chat_time;
	}

	public void setLast_chat_time(Date last_chat_time) {
		this.last_chat_time = last_chat_time;
	}
	
	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	
	public Integer getChat_days() {
		return chat_days;
	}

	public void setChat_days(Integer chat_days) {
		this.chat_days = chat_days;
	}
	
	public Integer getPay_times() {
		return pay_times;
	}

	public void setPay_times(Integer pay_times) {
		this.pay_times = pay_times;
	}
	
	public Long getPay_money() {
		return pay_money;
	}

	public void setPay_money(Long pay_money) {
		this.pay_money = pay_money;
	}
	
	public Long getDiamond() {
		return diamond;
	}

	public void setDiamond(Long diamond) {
		this.diamond = diamond;
	}
	
	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}
	
	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
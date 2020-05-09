package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户聊天数据统计[t_statistics_user_vchat_data] 表对应的实体类
 * @author lipeng
 * @Date 2019-07-10 17:18:54
 *
 */
@Table(name="t_statistics_user_vchat_data")
public class StatisticsUserVchatDataEntity extends BaseEntity implements Serializable {

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
	 * 新增或者累计数据生成的时间
	 */
	@Column(name="date",nullable=false,jdbcType=JdbcType.DATE,comment="新增或者累计数据生成的时间")
	private Date date;
	
	/**
	 * 0未知,1And,2IOS
	 */
	@Column(name="platform",nullable=false,jdbcType=JdbcType.TINYINT,comment="0未知,1And,2IOS")
	private Integer platform;
	
	/**
	 * 渠道名称
	 */
	@Column(name="channel",nullable=false,jdbcType=JdbcType.VARCHAR,comment="渠道名称")
	private String channel;
	
	/**
	 * 激活设备
	 */
	@Column(name="act_ples",nullable=false,jdbcType=JdbcType.BIGINT,comment="激活设备")
	private Long act_ples;
	
	/**
	 * 注册用户
	 */
	@Column(name="reg_ples",nullable=false,jdbcType=JdbcType.BIGINT,comment="注册用户")
	private Long reg_ples;
	
	/**
	 * 活跃用户
	 */
	@Column(name="active_user",nullable=false,jdbcType=JdbcType.BIGINT,comment="活跃用户")
	private Long active_user;
	
	/**
	 * 总拨打次数
	 */
	@Column(name="dial_count",nullable=false,jdbcType=JdbcType.BIGINT,comment="总拨打次数")
	private Long dial_count;
	
	/**
	 * 用户再聊时长
	 */
	@Column(name="call_duration",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户再聊时长")
	private Long call_duration;
	
	/**
	 * 通话次数
	 */
	@Column(name="call_count",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话次数")
	private Long call_count;
	
	/**
	 * 接通率
	 */
	@Column(name="call_rate",nullable=false,jdbcType=JdbcType.VARCHAR,comment="接通率")
	private String call_rate;
	
	/**
	 * 通话用户数
	 */
	@Column(name="call_user",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话用户数")
	private Long call_user;
	
	/**
	 * 单次时长(秒)
	 */
	@Column(name="once_duration",nullable=false,jdbcType=JdbcType.BIGINT,comment="单次时长(秒)")
	private Long once_duration;
	
	/**
	 * 付费人数
	 */
	@Column(name="pay_ples",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费人数")
	private Long pay_ples;
	
	/**
	 * 付费次数
	 */
	@Column(name="pay_stroke",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费次数")
	private Long pay_stroke;
	
	/**
	 * 付费率
	 */
	@Column(name="pay_rate",nullable=false,jdbcType=JdbcType.VARCHAR,comment="付费率")
	private String pay_rate;
	
	/**
	 * 复购率
	 */
	@Column(name="repurchase_rate",nullable=false,jdbcType=JdbcType.VARCHAR,comment="复购率")
	private String repurchase_rate;
	
	/**
	 * 付费金额
	 */
	@Column(name="pay_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费金额")
	private Long pay_total;
	
	/**
	 * 消耗
	 */
	@Column(name="consume_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="消耗")
	private Long consume_total;
	
	/**
	 * 补助
	 */
	@Column(name="subsidy_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="补助")
	private Long subsidy_total;
	
	/**
	 * 类型,0全部,1单渠道
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型,0全部,1单渠道")
	private Integer type;
	
	/**
	 * 用户类型 0：全用户 1：新用户
	 */
	@Column(name="user_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="用户类型 0：全用户 1：新用户")
	private Integer user_type;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public Long getAct_ples() {
		return act_ples;
	}

	public void setAct_ples(Long act_ples) {
		this.act_ples = act_ples;
	}
	
	public Long getReg_ples() {
		return reg_ples;
	}

	public void setReg_ples(Long reg_ples) {
		this.reg_ples = reg_ples;
	}
	
	public Long getActive_user() {
		return active_user;
	}

	public void setActive_user(Long active_user) {
		this.active_user = active_user;
	}
	
	public Long getDial_count() {
		return dial_count;
	}

	public void setDial_count(Long dial_count) {
		this.dial_count = dial_count;
	}
	
	public Long getCall_duration() {
		return call_duration;
	}

	public void setCall_duration(Long call_duration) {
		this.call_duration = call_duration;
	}
	
	public Long getCall_count() {
		return call_count;
	}

	public void setCall_count(Long call_count) {
		this.call_count = call_count;
	}
	
	public String getCall_rate() {
		return call_rate;
	}

	public void setCall_rate(String call_rate) {
		this.call_rate = call_rate;
	}
	
	public Long getCall_user() {
		return call_user;
	}

	public void setCall_user(Long call_user) {
		this.call_user = call_user;
	}
	
	public Long getOnce_duration() {
		return once_duration;
	}

	public void setOnce_duration(Long once_duration) {
		this.once_duration = once_duration;
	}
	
	public Long getPay_ples() {
		return pay_ples;
	}

	public void setPay_ples(Long pay_ples) {
		this.pay_ples = pay_ples;
	}
	
	public Long getPay_stroke() {
		return pay_stroke;
	}

	public void setPay_stroke(Long pay_stroke) {
		this.pay_stroke = pay_stroke;
	}
	
	public String getPay_rate() {
		return pay_rate;
	}

	public void setPay_rate(String pay_rate) {
		this.pay_rate = pay_rate;
	}
	
	public String getRepurchase_rate() {
		return repurchase_rate;
	}

	public void setRepurchase_rate(String repurchase_rate) {
		this.repurchase_rate = repurchase_rate;
	}
	
	public Long getPay_total() {
		return pay_total;
	}

	public void setPay_total(Long pay_total) {
		this.pay_total = pay_total;
	}
	
	public Long getConsume_total() {
		return consume_total;
	}

	public void setConsume_total(Long consume_total) {
		this.consume_total = consume_total;
	}
	
	public Long getSubsidy_total() {
		return subsidy_total;
	}

	public void setSubsidy_total(Long subsidy_total) {
		this.subsidy_total = subsidy_total;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	
}
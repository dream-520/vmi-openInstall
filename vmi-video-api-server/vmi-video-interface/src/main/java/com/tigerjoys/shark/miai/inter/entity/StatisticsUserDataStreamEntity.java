package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户数据流统计[t_statistics_user_data_stream] 表对应的实体类
 * @author lipeng
 * @Date 2019-06-21 12:03:49
 *
 */
@Table(name="t_statistics_user_data_stream")
public class StatisticsUserDataStreamEntity extends BaseEntity implements Serializable {

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
	 * 激活用户
	 */
	@Column(name="act_ples",nullable=false,jdbcType=JdbcType.BIGINT,comment="激活用户")
	private Long act_ples;
	
	/**
	 * 注册用户
	 */
	@Column(name="reg_ples",nullable=false,jdbcType=JdbcType.BIGINT,comment="注册用户")
	private Long reg_ples;
	
	/**
	 * 付费人数
	 */
	@Column(name="pay_ples",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费人数")
	private Long pay_ples;
	
	/**
	 * 付费笔数
	 */
	@Column(name="pay_stroke",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费笔数")
	private Long pay_stroke;
	
	/**
	 * 付费金额
	 */
	@Column(name="pay_total",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费金额")
	private Long pay_total;
	
	/**
	 * 活跃用户
	 */
	@Column(name="active_user",nullable=false,jdbcType=JdbcType.BIGINT,comment="活跃用户")
	private Long active_user;
	
	/**
	 * 拨打次数
	 */
	@Column(name="dial_count",nullable=false,jdbcType=JdbcType.BIGINT,comment="拨打次数")
	private Long dial_count;
	
	/**
	 * 拨打人数
	 */
	@Column(name="dial_user",nullable=false,jdbcType=JdbcType.BIGINT,comment="拨打人数")
	private Long dial_user;
	
	/**
	 * 通话次数
	 */
	@Column(name="call_count",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话次数")
	private Long call_count;
	
	/**
	 * 通话人数
	 */
	@Column(name="call_user",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话人数")
	private Long call_user;
	
	/**
	 * 通话时长
	 */
	@Column(name="call_duration",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话时长")
	private Long call_duration;
	
	/**
	 * 消费金额
	 */
	@Column(name="consume_count",nullable=false,jdbcType=JdbcType.BIGINT,comment="消费金额")
	private Long consume_count;
	
	
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
	
	public Long getPay_total() {
		return pay_total;
	}

	public void setPay_total(Long pay_total) {
		this.pay_total = pay_total;
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
	
	public Long getDial_user() {
		return dial_user;
	}

	public void setDial_user(Long dial_user) {
		this.dial_user = dial_user;
	}
	
	public Long getCall_count() {
		return call_count;
	}

	public void setCall_count(Long call_count) {
		this.call_count = call_count;
	}
	
	public Long getCall_user() {
		return call_user;
	}

	public void setCall_user(Long call_user) {
		this.call_user = call_user;
	}
	
	public Long getCall_duration() {
		return call_duration;
	}

	public void setCall_duration(Long call_duration) {
		this.call_duration = call_duration;
	}
	
	public Long getConsume_count() {
		return consume_count;
	}

	public void setConsume_count(Long consume_count) {
		this.consume_count = consume_count;
	}
	
}
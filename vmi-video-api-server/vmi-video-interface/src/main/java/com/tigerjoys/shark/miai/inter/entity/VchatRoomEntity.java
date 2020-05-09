package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_vchat_room] 表对应的实体类
 * @author yangjunming
 * @Date 2019-11-18 19:40:44
 *
 */
@Table(name="t_vchat_room")
public class VchatRoomEntity extends BaseEntity implements Serializable {

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
	 * 通话Id
	 */
	@Column(name="orderId",nullable=false,jdbcType=JdbcType.BIGINT,comment="通话Id")
	private Long orderId;
	
	/**
	 * 付费用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="付费用户ID")
	private Long userid;
	
	/**
	 * 主播用户ID
	 */
	@Column(name="anchorid",nullable=false,jdbcType=JdbcType.BIGINT,comment="主播用户ID")
	private Long anchorid;
	
	/**
	 * 工会ID 
	 */
	@Column(name="labor_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="工会ID ")
	private Long labor_id;
	
	/**
	 * 发起用户
	 */
	@Column(name="sponsor_user",nullable=false,jdbcType=JdbcType.BIGINT,comment="发起用户")
	private Long sponsor_user;
	
	/**
	 * 1 音频 2 视频  
	 */
	@Column(name="av_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="1 音频 2 视频  ")
	private Integer av_type;
	
	/**
	 * 主动关闭用户
	 */
	@Column(name="shut_user",nullable=true,jdbcType=JdbcType.BIGINT,comment="主动关闭用户")
	private Long shut_user;
	
	/**
	 * 退出原因
	 */
	@Column(name="shut_info",nullable=true,jdbcType=JdbcType.VARCHAR,comment="退出原因")
	private String shut_info;
	
	/**
	 * 钻石/每分钟
	 */
	@Column(name="price",nullable=false,jdbcType=JdbcType.INTEGER,comment="钻石/每分钟")
	private Integer price;
	
	/**
	 * 主播结算价
	 */
	@Column(name="settle_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播结算价")
	private Integer settle_price;
	
	/**
	 * 折扣
	 */
	@Column(name="pay_discount",nullable=true,jdbcType=JdbcType.DECIMAL,comment="折扣")
	private Double pay_discount;
	
	/**
	 * 总钻石
	 */
	@Column(name="total_price",nullable=false,jdbcType=JdbcType.INTEGER,comment="总钻石")
	private Integer total_price;
	
	/**
	 * 记费创建时间
	 */
	@Column(name="pay_create",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="记费创建时间")
	private Date pay_create;
	
	/**
	 * 聊天时长(分钟)
	 */
	@Column(name="vchat_duration",nullable=false,jdbcType=JdbcType.INTEGER,comment="聊天时长(分钟)")
	private Integer vchat_duration;
	
	/**
	 * 周卡时长
	 */
	@Column(name="weekcard_duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="周卡时长")
	private Integer weekcard_duration;
	
	/**
	 * 拨打标记
	 */
	@Column(name="dialing_falg",nullable=false,jdbcType=JdbcType.INTEGER,comment="拨打标记")
	private Integer dialing_falg;
	
	/**
	 * 网易通话标识
	 */
	@Column(name="wy_chatid",nullable=true,jdbcType=JdbcType.BIGINT,comment="网易通话标识")
	private Long wy_chatid;
	
	/**
	 * 记费时间
	 */
	@Column(name="pay_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="记费时间")
	private Date pay_time;
	
	/**
	 * 系统在聊时长(秒)
	 */
	@Column(name="sys_duration",nullable=false,jdbcType=JdbcType.INTEGER,comment="系统在聊时长(秒)")
	private Integer sys_duration;
	
	/**
	 *  初始无收益  1 待结算 2 无收益 3 已入账
	 */
	@Column(name="income_falg",nullable=true,jdbcType=JdbcType.TINYINT,comment=" 初始无收益  1 待结算 2 无收益 3 已入账")
	private Integer income_falg;
	
	/**
	 * 是否扣量 0 不扣 1 抵扣 
	 */
	@Column(name="offset_flag",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否扣量 0 不扣 1 抵扣 ")
	private Integer offset_flag;
	
	/**
	 * 偏移时长 
	 */
	@Column(name="sys_offset_duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="偏移时长 ")
	private Integer sys_offset_duration;
	
	/**
	 * 是否有视频(1 有 0 没有)
	 */
	@Column(name="video_falg",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否有视频(1 有 0 没有)")
	private Integer video_falg;
	
	/**
	 * 0 老用户 1 新用户 
	 */
	@Column(name="user_flag",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 老用户 1 新用户 ")
	private Integer user_flag;
	
	/**
	 * 0 正常 1 警告  2 违规退出
	 */
	@Column(name="porn_falg",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 正常 1 警告  2 违规退出")
	private Integer porn_falg;
	
	/**
	 * 聊天时长
	 */
	@Column(name="duration",nullable=false,jdbcType=JdbcType.INTEGER,comment="聊天时长")
	private Integer duration;
	
	/**
	 * 结算 类型  0 分钟  1 10秒
	 */
	@Column(name="settlement_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="结算 类型  0 分钟  1 10秒")
	private Integer settlement_type;
	
	/**
	 * 0 不兔费 1兔费 2 免费后充钻
	 */
	@Column(name="free_vchart_falg",nullable=false,jdbcType=JdbcType.TINYINT,comment="0 不兔费 1兔费 2 免费后充钻")
	private Integer free_vchart_falg;
	
	/**
	 * 奖励方案 0 原始奖励方案,1 新奖励方案,2 不奖励
	 */
	@Column(name="free_income_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="奖励方案 0 原始奖励方案,1 新奖励方案,2 不奖励")
	private Integer free_income_type;
	
	/**
	 * 0 体验无收益  1 待结算 2 无收益 3 已入账
	 */
	@Column(name="free_income_falg",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 体验无收益  1 待结算 2 无收益 3 已入账")
	private Integer free_income_falg;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
	/**
	 * 结束时间
	 */
	@Column(name="finish_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="结束时间")
	private Date finish_time;
	
	/**
	 * 色情标签标识 0 正常 1 用户色情 2 主播色情 3 同时色情
	 */
	@Column(name="check_falg",nullable=true,jdbcType=JdbcType.TINYINT,comment="色情标签标识 0 正常 1 用户色情 2 主播色情 3 同时色情")
	private Integer check_falg;
	
	/**
	 * 自动拨打标记 0 人工 1自动
	 */
	@Column(name="auto_flag",nullable=true,jdbcType=JdbcType.TINYINT,comment="自动拨打标记 0 人工 1自动")
	private Integer auto_flag;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Long getLabor_id() {
		return labor_id;
	}

	public void setLabor_id(Long labor_id) {
		this.labor_id = labor_id;
	}
	
	public Long getSponsor_user() {
		return sponsor_user;
	}

	public void setSponsor_user(Long sponsor_user) {
		this.sponsor_user = sponsor_user;
	}
	
	public Integer getAv_type() {
		return av_type;
	}

	public void setAv_type(Integer av_type) {
		this.av_type = av_type;
	}
	
	public Long getShut_user() {
		return shut_user;
	}

	public void setShut_user(Long shut_user) {
		this.shut_user = shut_user;
	}
	
	public String getShut_info() {
		return shut_info;
	}

	public void setShut_info(String shut_info) {
		this.shut_info = shut_info;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getSettle_price() {
		return settle_price;
	}

	public void setSettle_price(Integer settle_price) {
		this.settle_price = settle_price;
	}
	
	public Double getPay_discount() {
		return pay_discount;
	}

	public void setPay_discount(Double pay_discount) {
		this.pay_discount = pay_discount;
	}
	
	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
	
	public Date getPay_create() {
		return pay_create;
	}

	public void setPay_create(Date pay_create) {
		this.pay_create = pay_create;
	}
	
	public Integer getVchat_duration() {
		return vchat_duration;
	}

	public void setVchat_duration(Integer vchat_duration) {
		this.vchat_duration = vchat_duration;
	}
	
	public Integer getWeekcard_duration() {
		return weekcard_duration;
	}

	public void setWeekcard_duration(Integer weekcard_duration) {
		this.weekcard_duration = weekcard_duration;
	}
	
	public Integer getDialing_falg() {
		return dialing_falg;
	}

	public void setDialing_falg(Integer dialing_falg) {
		this.dialing_falg = dialing_falg;
	}
	
	public Long getWy_chatid() {
		return wy_chatid;
	}

	public void setWy_chatid(Long wy_chatid) {
		this.wy_chatid = wy_chatid;
	}
	
	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	
	public Integer getSys_duration() {
		return sys_duration;
	}

	public void setSys_duration(Integer sys_duration) {
		this.sys_duration = sys_duration;
	}
	
	public Integer getIncome_falg() {
		return income_falg;
	}

	public void setIncome_falg(Integer income_falg) {
		this.income_falg = income_falg;
	}
	
	public Integer getOffset_flag() {
		return offset_flag;
	}

	public void setOffset_flag(Integer offset_flag) {
		this.offset_flag = offset_flag;
	}
	
	public Integer getSys_offset_duration() {
		return sys_offset_duration;
	}

	public void setSys_offset_duration(Integer sys_offset_duration) {
		this.sys_offset_duration = sys_offset_duration;
	}
	
	public Integer getVideo_falg() {
		return video_falg;
	}

	public void setVideo_falg(Integer video_falg) {
		this.video_falg = video_falg;
	}
	
	public Integer getUser_flag() {
		return user_flag;
	}

	public void setUser_flag(Integer user_flag) {
		this.user_flag = user_flag;
	}
	
	public Integer getPorn_falg() {
		return porn_falg;
	}

	public void setPorn_falg(Integer porn_falg) {
		this.porn_falg = porn_falg;
	}
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public Integer getSettlement_type() {
		return settlement_type;
	}

	public void setSettlement_type(Integer settlement_type) {
		this.settlement_type = settlement_type;
	}
	
	public Integer getFree_vchart_falg() {
		return free_vchart_falg;
	}

	public void setFree_vchart_falg(Integer free_vchart_falg) {
		this.free_vchart_falg = free_vchart_falg;
	}
	
	public Integer getFree_income_type() {
		return free_income_type;
	}

	public void setFree_income_type(Integer free_income_type) {
		this.free_income_type = free_income_type;
	}
	
	public Integer getFree_income_falg() {
		return free_income_falg;
	}

	public void setFree_income_falg(Integer free_income_falg) {
		this.free_income_falg = free_income_falg;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}
	
	public Integer getCheck_falg() {
		return check_falg;
	}

	public void setCheck_falg(Integer check_falg) {
		this.check_falg = check_falg;
	}
	
	public Integer getAuto_flag() {
		return auto_flag;
	}

	public void setAuto_flag(Integer auto_flag) {
		this.auto_flag = auto_flag;
	}
	
}
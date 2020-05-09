package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播数据流统计[t_statistics_anchor_data_stream] 表对应的实体类
 * @author yangjunming
 * @Date 2019-11-02 18:20:53
 *
 */
@Table(name="t_statistics_anchor_data_stream")
public class StatisticsAnchorDataStreamEntity extends BaseEntity implements Serializable {

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
	 * 总通话主播
	 */
	@Column(name="anchorid_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="总通话主播")
	private Integer anchorid_num;
	
	/**
	 * 总收益钻
	 */
	@Column(name="total_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="总收益钻")
	private Integer total_price;
	
	/**
	 * 总拨打次数
	 */
	@Column(name="total_dial",nullable=true,jdbcType=JdbcType.INTEGER,comment="总拨打次数")
	private Integer total_dial;
	
	/**
	 * 总通话次数
	 */
	@Column(name="total_recv",nullable=true,jdbcType=JdbcType.INTEGER,comment="总通话次数")
	private Integer total_recv;
	
	/**
	 * 总拨打用户数
	 */
	@Column(name="dial_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="总拨打用户数")
	private Integer dial_user_num;
	
	/**
	 * 总通话用户数
	 */
	@Column(name="recv_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="总通话用户数")
	private Integer recv_user_num;
	
	/**
	 * 接通率
	 */
	@Column(name="avg_recv_times",nullable=true,jdbcType=JdbcType.DECIMAL,comment="接通率")
	private Double avg_recv_times;
	
	/**
	 * 单次时长
	 */
	@Column(name="avg_recv_duration",nullable=true,jdbcType=JdbcType.DECIMAL,comment="单次时长")
	private Double avg_recv_duration;
	
	/**
	 * 主播打次数
	 */
	@Column(name="anchor_dial_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播打次数")
	private Integer anchor_dial_times;
	
	/**
	 * 拨打主播数P
	 */
	@Column(name="anchor_dial_anchor_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="拨打主播数P")
	private Integer anchor_dial_anchor_num;
	
	/**
	 * 拨通主播数P
	 */
	@Column(name="anchor_dial_anchor_dial_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="拨通主播数P")
	private Integer anchor_dial_anchor_dial_num;
	
	/**
	 * 用户接通次数
	 */
	@Column(name="anchor_dial_recv_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户接通次数")
	private Integer anchor_dial_recv_times;
	
	/**
	 * 用户数
	 */
	@Column(name="anchor_dial_recv_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户数")
	private Integer anchor_dial_recv_user_num;
	
	/**
	 * 被打用户数 
	 */
	@Column(name="anchor_dial_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="被打用户数 ")
	private Integer anchor_dial_user_num;
	
	/**
	 * 用户接通率
	 */
	@Column(name="avg_anchor_dial_recv",nullable=true,jdbcType=JdbcType.DECIMAL,comment="用户接通率")
	private Double avg_anchor_dial_recv;
	
	/**
	 * 转化付费次数
	 */
	@Column(name="anchor_trans_user_pay_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费次数")
	private Integer anchor_trans_user_pay_times;
	
	/**
	 * 转化付费金额
	 */
	@Column(name="anchor_trans_user_pay_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费金额")
	private Integer anchor_trans_user_pay_money;
	
	/**
	 * 转化付费人数
	 */
	@Column(name="anchor_trans_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费人数")
	private Integer anchor_trans_user_num;
	
	/**
	 * 转化比例
	 */
	@Column(name="avg_anchor_trans_user",nullable=true,jdbcType=JdbcType.DECIMAL,comment="转化比例")
	private Double avg_anchor_trans_user;
	
	/**
	 * 用户打次数
	 */
	@Column(name="user_dial_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户打次数")
	private Integer user_dial_times;
	
	/**
	 * 拨打用户数M
	 */
	@Column(name="user_dial_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="拨打用户数M")
	private Integer user_dial_num;
	
	/**
	 * 拨通用户数P
	 */
	@Column(name="user_dial_user_dial_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="拨通用户数P")
	private Integer user_dial_user_dial_num;
	
	/**
	 * 主播接通次数
	 */
	@Column(name="user_dial_recv_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播接通次数")
	private Integer user_dial_recv_times;
	
	/**
	 * 用户拨打接听主播人数M
	 */
	@Column(name="user_dial_recv_anchor_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户拨打接听主播人数M")
	private Integer user_dial_recv_anchor_num;
	
	/**
	 * 主播接通率
	 */
	@Column(name="avg_user_dial_recv",nullable=true,jdbcType=JdbcType.DECIMAL,comment="主播接通率")
	private Double avg_user_dial_recv;
	
	/**
	 * 转化付费次数
	 */
	@Column(name="user_dial_trans_user_pay_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费次数")
	private Integer user_dial_trans_user_pay_times;
	
	/**
	 * 转化付费金额
	 */
	@Column(name="user_dial_trans_user_pay_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费金额")
	private Integer user_dial_trans_user_pay_money;
	
	/**
	 * 转化付费人数
	 */
	@Column(name="user_dial_trans_user_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="转化付费人数")
	private Integer user_dial_trans_user_num;
	
	/**
	 * 转化比例
	 */
	@Column(name="avg_user_trans_anchor",nullable=true,jdbcType=JdbcType.DECIMAL,comment="转化比例")
	private Double avg_user_trans_anchor;
	
	/**
	 * 下传短视频
	 */
	@Column(name="shortVideo_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="下传短视频")
	private Integer shortVideo_num;
	
	/**
	 * 总收益(元)
	 */
	@Column(name="anchor_total_money",nullable=true,jdbcType=JdbcType.DECIMAL,comment="总收益(元)")
	private Double anchor_total_money;
	
	/**
	 * 0 明细  1 汇总
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="0 明细  1 汇总")
	private Integer type;
	
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
	
	public Integer getAnchorid_num() {
		return anchorid_num;
	}

	public void setAnchorid_num(Integer anchorid_num) {
		this.anchorid_num = anchorid_num;
	}
	
	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
	
	public Integer getTotal_dial() {
		return total_dial;
	}

	public void setTotal_dial(Integer total_dial) {
		this.total_dial = total_dial;
	}
	
	public Integer getTotal_recv() {
		return total_recv;
	}

	public void setTotal_recv(Integer total_recv) {
		this.total_recv = total_recv;
	}
	
	public Integer getDial_user_num() {
		return dial_user_num;
	}

	public void setDial_user_num(Integer dial_user_num) {
		this.dial_user_num = dial_user_num;
	}
	
	public Integer getRecv_user_num() {
		return recv_user_num;
	}

	public void setRecv_user_num(Integer recv_user_num) {
		this.recv_user_num = recv_user_num;
	}
	
	public Double getAvg_recv_times() {
		return avg_recv_times;
	}

	public void setAvg_recv_times(Double avg_recv_times) {
		this.avg_recv_times = avg_recv_times;
	}
	
	public Double getAvg_recv_duration() {
		return avg_recv_duration;
	}

	public void setAvg_recv_duration(Double avg_recv_duration) {
		this.avg_recv_duration = avg_recv_duration;
	}
	
	public Integer getAnchor_dial_times() {
		return anchor_dial_times;
	}

	public void setAnchor_dial_times(Integer anchor_dial_times) {
		this.anchor_dial_times = anchor_dial_times;
	}
	
	public Integer getAnchor_dial_anchor_num() {
		return anchor_dial_anchor_num;
	}

	public void setAnchor_dial_anchor_num(Integer anchor_dial_anchor_num) {
		this.anchor_dial_anchor_num = anchor_dial_anchor_num;
	}
	
	public Integer getAnchor_dial_anchor_dial_num() {
		return anchor_dial_anchor_dial_num;
	}

	public void setAnchor_dial_anchor_dial_num(Integer anchor_dial_anchor_dial_num) {
		this.anchor_dial_anchor_dial_num = anchor_dial_anchor_dial_num;
	}
	
	public Integer getAnchor_dial_recv_times() {
		return anchor_dial_recv_times;
	}

	public void setAnchor_dial_recv_times(Integer anchor_dial_recv_times) {
		this.anchor_dial_recv_times = anchor_dial_recv_times;
	}
	
	public Integer getAnchor_dial_recv_user_num() {
		return anchor_dial_recv_user_num;
	}

	public void setAnchor_dial_recv_user_num(Integer anchor_dial_recv_user_num) {
		this.anchor_dial_recv_user_num = anchor_dial_recv_user_num;
	}
	
	public Integer getAnchor_dial_user_num() {
		return anchor_dial_user_num;
	}

	public void setAnchor_dial_user_num(Integer anchor_dial_user_num) {
		this.anchor_dial_user_num = anchor_dial_user_num;
	}
	
	public Double getAvg_anchor_dial_recv() {
		return avg_anchor_dial_recv;
	}

	public void setAvg_anchor_dial_recv(Double avg_anchor_dial_recv) {
		this.avg_anchor_dial_recv = avg_anchor_dial_recv;
	}
	
	public Integer getAnchor_trans_user_pay_times() {
		return anchor_trans_user_pay_times;
	}

	public void setAnchor_trans_user_pay_times(Integer anchor_trans_user_pay_times) {
		this.anchor_trans_user_pay_times = anchor_trans_user_pay_times;
	}
	
	public Integer getAnchor_trans_user_pay_money() {
		return anchor_trans_user_pay_money;
	}

	public void setAnchor_trans_user_pay_money(Integer anchor_trans_user_pay_money) {
		this.anchor_trans_user_pay_money = anchor_trans_user_pay_money;
	}
	
	public Integer getAnchor_trans_user_num() {
		return anchor_trans_user_num;
	}

	public void setAnchor_trans_user_num(Integer anchor_trans_user_num) {
		this.anchor_trans_user_num = anchor_trans_user_num;
	}
	
	public Double getAvg_anchor_trans_user() {
		return avg_anchor_trans_user;
	}

	public void setAvg_anchor_trans_user(Double avg_anchor_trans_user) {
		this.avg_anchor_trans_user = avg_anchor_trans_user;
	}
	
	public Integer getUser_dial_times() {
		return user_dial_times;
	}

	public void setUser_dial_times(Integer user_dial_times) {
		this.user_dial_times = user_dial_times;
	}
	
	public Integer getUser_dial_num() {
		return user_dial_num;
	}

	public void setUser_dial_num(Integer user_dial_num) {
		this.user_dial_num = user_dial_num;
	}
	
	public Integer getUser_dial_user_dial_num() {
		return user_dial_user_dial_num;
	}

	public void setUser_dial_user_dial_num(Integer user_dial_user_dial_num) {
		this.user_dial_user_dial_num = user_dial_user_dial_num;
	}
	
	public Integer getUser_dial_recv_times() {
		return user_dial_recv_times;
	}

	public void setUser_dial_recv_times(Integer user_dial_recv_times) {
		this.user_dial_recv_times = user_dial_recv_times;
	}
	
	public Integer getUser_dial_recv_anchor_num() {
		return user_dial_recv_anchor_num;
	}

	public void setUser_dial_recv_anchor_num(Integer user_dial_recv_anchor_num) {
		this.user_dial_recv_anchor_num = user_dial_recv_anchor_num;
	}
	
	public Double getAvg_user_dial_recv() {
		return avg_user_dial_recv;
	}

	public void setAvg_user_dial_recv(Double avg_user_dial_recv) {
		this.avg_user_dial_recv = avg_user_dial_recv;
	}
	
	public Integer getUser_dial_trans_user_pay_times() {
		return user_dial_trans_user_pay_times;
	}

	public void setUser_dial_trans_user_pay_times(Integer user_dial_trans_user_pay_times) {
		this.user_dial_trans_user_pay_times = user_dial_trans_user_pay_times;
	}
	
	public Integer getUser_dial_trans_user_pay_money() {
		return user_dial_trans_user_pay_money;
	}

	public void setUser_dial_trans_user_pay_money(Integer user_dial_trans_user_pay_money) {
		this.user_dial_trans_user_pay_money = user_dial_trans_user_pay_money;
	}
	
	public Integer getUser_dial_trans_user_num() {
		return user_dial_trans_user_num;
	}

	public void setUser_dial_trans_user_num(Integer user_dial_trans_user_num) {
		this.user_dial_trans_user_num = user_dial_trans_user_num;
	}
	
	public Double getAvg_user_trans_anchor() {
		return avg_user_trans_anchor;
	}

	public void setAvg_user_trans_anchor(Double avg_user_trans_anchor) {
		this.avg_user_trans_anchor = avg_user_trans_anchor;
	}
	
	public Integer getShortVideo_num() {
		return shortVideo_num;
	}

	public void setShortVideo_num(Integer shortVideo_num) {
		this.shortVideo_num = shortVideo_num;
	}
	
	public Double getAnchor_total_money() {
		return anchor_total_money;
	}

	public void setAnchor_total_money(Double anchor_total_money) {
		this.anchor_total_money = anchor_total_money;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
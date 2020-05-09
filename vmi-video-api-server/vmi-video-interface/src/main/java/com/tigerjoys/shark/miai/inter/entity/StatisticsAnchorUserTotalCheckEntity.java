package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播和用户汇总统计[t_statistics_anchor_user_total_check] 表对应的实体类
 * @author yangjunming
 * @Date 2019-08-22 16:09:00
 *
 */
@Table(name="t_statistics_anchor_user_total_check")
public class StatisticsAnchorUserTotalCheckEntity extends BaseEntity implements Serializable {

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
	 * 总收益钻
	 */
	@Column(name="total_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="总收益钻")
	private Integer total_price;
	
	/**
	 * 总通话主播
	 */
	@Column(name="anchorid_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="总通话主播")
	private Integer anchorid_num;
	
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
	@Column(name="avg_recv_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="接通率")
	private Integer avg_recv_times;
	
	/**
	 * 单次时长
	 */
	@Column(name="avg_recv_duration",nullable=true,jdbcType=JdbcType.INTEGER,comment="单次时长")
	private Integer avg_recv_duration;
	
	/**
	 * 上传短视频
	 */
	@Column(name="shortVideo_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="上传短视频")
	private Integer shortVideo_num;
	
	/**
	 * 总收益(元)
	 */
	@Column(name="anchor_total_money",nullable=true,jdbcType=JdbcType.DECIMAL,comment="总收益(元)")
	private Double anchor_total_money;
	
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
	
	public Integer getAnchorid_num() {
		return anchorid_num;
	}

	public void setAnchorid_num(Integer anchorid_num) {
		this.anchorid_num = anchorid_num;
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
	
	public Integer getAvg_recv_times() {
		return avg_recv_times;
	}

	public void setAvg_recv_times(Integer avg_recv_times) {
		this.avg_recv_times = avg_recv_times;
	}
	
	public Integer getAvg_recv_duration() {
		return avg_recv_duration;
	}

	public void setAvg_recv_duration(Integer avg_recv_duration) {
		this.avg_recv_duration = avg_recv_duration;
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
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
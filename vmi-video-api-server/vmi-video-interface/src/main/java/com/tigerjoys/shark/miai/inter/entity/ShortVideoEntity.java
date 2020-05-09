package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_short_video] 表对应的实体类
 * @author yangjunming
 * @Date 2019-01-02 15:45:57
 *
 */
@Table(name="t_short_video")
public class ShortVideoEntity extends BaseEntity implements Serializable {

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
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 视频封面
	 */
	@Column(name="video_photo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="视频封面")
	private String video_photo;
	
	/**
	 * 视频地址
	 */
	@Column(name="video_path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="视频地址")
	private String video_path;
	
	/**
	 * 点赞数
	 */
	@Column(name="video_praise",nullable=true,jdbcType=JdbcType.INTEGER,comment="点赞数")
	private Integer video_praise;
	
	/**
	 * 观看人数
	 */
	@Column(name="watch_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="观看人数")
	private Integer watch_num;
	
	/**
	 * 状态 1 上架  0 主动下架 -1 被动下架   -9 删除
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态 1 上架  0 主动下架 -1 被动下架   -9 删除")
	private Integer status;
	
	/**
	 * 日榜
	 */
	@Column(name="ranking_day",nullable=true,jdbcType=JdbcType.INTEGER,comment="日榜")
	private Integer ranking_day;
	
	/**
	 * 周榜
	 */
	@Column(name="ranking_week",nullable=true,jdbcType=JdbcType.INTEGER,comment="周榜")
	private Integer ranking_week;
	
	/**
	 * 月榜
	 */
	@Column(name="ranking_month",nullable=true,jdbcType=JdbcType.INTEGER,comment="月榜")
	private Integer ranking_month;
	
	/**
	 * 是否对图片进行模糊 0 不模糊  1 模糊
	 */
	@Column(name="obscure",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否对图片进行模糊 0 不模糊  1 模糊")
	private Integer obscure;
	
	/**
	 * 图片模糊路径
	 */
	@Column(name="obscure_path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="图片模糊路径")
	private String obscure_path;
	
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
	
	public String getVideo_photo() {
		return video_photo;
	}

	public void setVideo_photo(String video_photo) {
		this.video_photo = video_photo;
	}
	
	public String getVideo_path() {
		return video_path;
	}

	public void setVideo_path(String video_path) {
		this.video_path = video_path;
	}
	
	public Integer getVideo_praise() {
		return video_praise;
	}

	public void setVideo_praise(Integer video_praise) {
		this.video_praise = video_praise;
	}
	
	public Integer getWatch_num() {
		return watch_num;
	}

	public void setWatch_num(Integer watch_num) {
		this.watch_num = watch_num;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getRanking_day() {
		return ranking_day;
	}

	public void setRanking_day(Integer ranking_day) {
		this.ranking_day = ranking_day;
	}
	
	public Integer getRanking_week() {
		return ranking_week;
	}

	public void setRanking_week(Integer ranking_week) {
		this.ranking_week = ranking_week;
	}
	
	public Integer getRanking_month() {
		return ranking_month;
	}

	public void setRanking_month(Integer ranking_month) {
		this.ranking_month = ranking_month;
	}

	public Integer getObscure() {
		return obscure;
	}

	public void setObscure(Integer obscure) {
		this.obscure = obscure;
	}

	public String getObscure_path() {
		return obscure_path;
	}

	public void setObscure_path(String obscure_path) {
		this.obscure_path = obscure_path;
	}
	
}
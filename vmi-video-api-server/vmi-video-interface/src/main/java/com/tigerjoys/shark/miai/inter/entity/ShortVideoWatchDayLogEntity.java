package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  短视频每日观看数[t_short_video_watch_day_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-01-02 16:04:33
 *
 */
@Table(name="t_short_video_watch_day_log")
public class ShortVideoWatchDayLogEntity extends BaseEntity implements Serializable {

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
	 * video_id
	 */
	@Column(name="video_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="video_id")
	private Long video_id;
	
	/**
	 * watch_time
	 */
	@Column(name="watch_time",nullable=true,jdbcType=JdbcType.INTEGER,comment="watch_time")
	private Integer watch_time;
	
	/**
	 * watch_num
	 */
	@Column(name="watch_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="watch_num")
	private Integer watch_num;
	
	/**
	 * create_time
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="create_time")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVideo_id() {
		return video_id;
	}

	public void setVideo_id(Long video_id) {
		this.video_id = video_id;
	}
	
	public Integer getWatch_time() {
		return watch_time;
	}

	public void setWatch_time(Integer watch_time) {
		this.watch_time = watch_time;
	}
	
	public Integer getWatch_num() {
		return watch_num;
	}

	public void setWatch_num(Integer watch_num) {
		this.watch_num = watch_num;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
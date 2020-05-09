package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  短视频转码表[t_short_video_transcode] 表对应的实体类
 * @author yangjunming
 * @Date 2019-04-12 15:41:53
 *
 */
@Table(name="t_short_video_transcode")
public class ShortVideoTranscodeEntity extends BaseEntity implements Serializable {

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
	 * 视频ID
	 */
	@Column(name="videoId",nullable=true,jdbcType=JdbcType.BIGINT,comment="视频ID")
	private Long videoId;
	
	/**
	 * 任务ID
	 */
	@Column(name="taskId",nullable=true,jdbcType=JdbcType.VARCHAR,comment="任务ID")
	private String taskId;
	
	/**
	 * 服务空间名
	 */
	@Column(name="service",nullable=true,jdbcType=JdbcType.VARCHAR,comment="服务空间名")
	private String service;
	
	/**
	 * 返回状态
	 */
	@Column(name="result_status",nullable=true,jdbcType=JdbcType.INTEGER,comment="返回状态")
	private Integer result_status;
	
	/**
	 * 处理状态
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.INTEGER,comment="处理状态")
	private Integer status;
	
	/**
	 * 地址
	 */
	@Column(name="path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="地址")
	private String path;
	
	/**
	 * 描术
	 */
	@Column(name="description",nullable=true,jdbcType=JdbcType.VARCHAR,comment="描术")
	private String description;
	
	/**
	 * 转码次数
	 */
	@Column(name="trans_times",nullable=true,jdbcType=JdbcType.INTEGER,comment="转码次数")
	private Integer trans_times;
	
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	public Integer getResult_status() {
		return result_status;
	}

	public void setResult_status(Integer result_status) {
		this.result_status = result_status;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getTrans_times() {
		return trans_times;
	}

	public void setTrans_times(Integer trans_times) {
		this.trans_times = trans_times;
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
	
}
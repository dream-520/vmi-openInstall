package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播音频列表数据[t_anchor_audio] 表对应的实体类
 * @author shiming
 * @Date 2019-08-06 19:46:56
 *
 */
@Table(name="t_anchor_audio")
public class AnchorAudioEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标识
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标识")
	private Long id;
	
	/**
	 * 主播id标识
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播id标识")
	private Long userid;
	
	/**
	 * 音频上传路径
	 */
	@Column(name="url",nullable=true,jdbcType=JdbcType.VARCHAR,comment="音频上传路径")
	private String url;
	
	/**
	 * 音频时长
	 */
	@Column(name="audioTime",nullable=true,jdbcType=JdbcType.INTEGER,comment="音频时长")
	private Integer audioTime;
	
	/**
	 * 音频状态 -9删除 0 未选择 1 选中
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="音频状态 -9删除 0 未选择 1 选中")
	private Integer status;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 修改时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="修改时间")
	private Date update_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getAudioTime() {
		return audioTime;
	}

	public void setAudioTime(Integer audioTime) {
		this.audioTime = audioTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
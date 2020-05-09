package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  网易通话信息记录表[t_vchat_channel] 表对应的实体类
 * @author shiming
 * @Date 2019-06-21 14:39:20
 *
 */
@Table(name="t_vchat_channel")
public class VchatChannelEntity extends BaseEntity implements Serializable {

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
	 * 发起方
	 */
	@Column(name="callerid",nullable=true,jdbcType=JdbcType.BIGINT,comment="发起方")
	private Long callerid;
	
	/**
	 * 接收方
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="接收方")
	private Long userid;
	
	/**
	 * 网易通话标识
	 */
	@Column(name="channelId",nullable=false,jdbcType=JdbcType.BIGINT,comment="网易通话标识")
	private Long channelId;
	
	/**
	 * 拨打时长
	 */
	@Column(name="duration",nullable=true,jdbcType=JdbcType.SMALLINT,comment="拨打时长")
	private Integer duration;
	
	/**
	 * 状态 0 待处理 1 下载并上传成功 2 下载失败 3 未找到对应通话标识  4完成对应关系
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态 0 待处理 1 下载并上传成功 2 下载失败 3 未找到对应通话标识  4完成对应关系")
	private Integer state;
	
	/**
	 * 网易视频地址
	 */
	@Column(name="url",nullable=true,jdbcType=JdbcType.VARCHAR,comment="网易视频地址")
	private String url;
	
	/**
	 * 本地存储位置
	 */
	@Column(name="path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="本地存储位置")
	private String path;
	
	/**
	 * 截图地址
	 */
	@Column(name="photo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="截图地址")
	private String photo;
	
	/**
	 * 标识是否是那种类型的文件 0 视频 1 音频
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="标识是否是那种类型的文件 0 视频 1 音频")
	private Integer type;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
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
	
	public Long getCallerid() {
		return callerid;
	}

	public void setCallerid(Long callerid) {
		this.callerid = callerid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}
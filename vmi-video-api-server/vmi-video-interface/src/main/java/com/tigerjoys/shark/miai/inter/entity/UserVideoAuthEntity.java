package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户视频认证提交表[t_user_video_auth] 表对应的实体类
 * @author lipeng
 * @Date 2017-09-19 10:03:42
 *
 */
@Table(name="t_user_video_auth")
public class UserVideoAuthEntity extends BaseEntity implements Serializable {

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
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 认证视频
	 */
	@Column(name="video_auth",nullable=false,jdbcType=JdbcType.VARCHAR,comment="认证视频")
	private String video_auth;
	
	/**
	 * 认证视频展示图片
	 */
	@Column(name="video_auth_pic",nullable=false,jdbcType=JdbcType.VARCHAR,comment="认证视频展示图片")
	private String video_auth_pic;
	
	/**
	 * 认证状态：1成功,2失败,0认证中
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="认证状态：1成功,2失败,0认证中")
	private Integer status;
	
	/**
	 * 认证备注
	 */
	@Column(name="memo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="认证备注")
	private String memo;
	
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
	
	public String getVideo_auth() {
		return video_auth;
	}

	public void setVideo_auth(String video_auth) {
		this.video_auth = video_auth;
	}
	
	public String getVideo_auth_pic() {
		return video_auth_pic;
	}

	public void setVideo_auth_pic(String video_auth_pic) {
		this.video_auth_pic = video_auth_pic;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  我的页面活动表[t_user_mypage_activity] 表对应的实体类
 * @author lipeng
 * @Date 2017-09-12 11:25:26
 *
 */
@Table(name="t_user_mypage_activity")
public class UserMypageActivityEntity extends BaseEntity implements Serializable {

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
	 * 目录标号
	 */
	@Column(name="index_code",nullable=false,jdbcType=JdbcType.INTEGER,comment="目录标号")
	private Integer index_code;
	
	/**
	 * 活动标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="活动标题")
	private String title;
	
	/**
	 * 活动地址
	 */
	@Column(name="url",nullable=false,jdbcType=JdbcType.VARCHAR,comment="活动地址")
	private String url;
	
	/**
	 * 标题图标
	 */
	@Column(name="icon",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标题图标")
	private String icon;
	
	/**
	 * 0启动,1关闭
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="0启动,1关闭")
	private Integer status;
	
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
	
	public Integer getIndex_code() {
		return index_code;
	}

	public void setIndex_code(Integer index_code) {
		this.index_code = index_code;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
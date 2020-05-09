package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_app_tab] 表对应的实体类
 * @author shiming
 * @Date 2018-12-27 18:17:21
 *
 */
@Table(name="t_app_tab")
public class AppTabEntity extends BaseEntity implements Serializable {

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
	 * 标签名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标签名称")
	private String name;
	
	/**
	 * 标签标识
	 */
	@Column(name="tag",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标签标识")
	private String tag;
	
	/**
	 * 标签下是否存在 banner
	 */
	@Column(name="banner",nullable=true,jdbcType=JdbcType.SMALLINT,comment="标签下是否存在 banner")
	private Integer banner;
	
	/**
	 * 优先级
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.SMALLINT,comment="优先级")
	private Integer priority;
	
	/**
	 * 标签状态 1 正常 -8 关闭
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.SMALLINT,comment="标签状态 1 正常 -8 关闭")
	private Integer state;
	
	/**
	 * 备注
	 */
	@Column(name="meno",nullable=true,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String meno;
	
	/**
	 * 标签类型 1 首页标签 2 视频标签
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.SMALLINT,comment="标签类型 1 首页标签 2 视频标签")
	private Integer type;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Integer getBanner() {
		return banner;
	}

	public void setBanner(Integer banner) {
		this.banner = banner;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
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
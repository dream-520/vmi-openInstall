package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户举报表[t_user_inform] 表对应的实体类
 * @author lipeng
 * @Date 2017-08-17 11:03:29
 *
 */
@Table(name="t_user_inform")
public class UserInformEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="主键ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 被举报用户ID
	 */
	@Column(name="informid",nullable=false,jdbcType=JdbcType.BIGINT,comment="被举报用户ID")
	private Long informid;
	
	/**
	 * 举报类型
	 */
	@Column(name="inform_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="举报类型")
	private Integer inform_type;
	
	/**
	 * 举报内容
	 */
	@Column(name="inform_info",nullable=false,jdbcType=JdbcType.VARCHAR,comment="举报内容")
	private String inform_info;
	
	/**
	 * 处理状态 0未处理  1已处理
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.TINYINT,comment="处理状态 0未处理  1已处理")
	private Integer state;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getInformid() {
		return informid;
	}

	public void setInformid(Long informid) {
		this.informid = informid;
	}
	
	public Integer getInform_type() {
		return inform_type;
	}

	public void setInform_type(Integer inform_type) {
		this.inform_type = inform_type;
	}
	
	public String getInform_info() {
		return inform_info;
	}

	public void setInform_info(String inform_info) {
		this.inform_info = inform_info;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
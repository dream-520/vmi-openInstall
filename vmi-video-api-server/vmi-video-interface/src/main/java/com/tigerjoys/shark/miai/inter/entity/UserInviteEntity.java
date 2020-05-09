package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户邀请关系表[t_user_invite] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-16 17:47:10
 *
 */
@Table(name="t_user_invite")
public class UserInviteEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 映射关系
	 */
	@Column(name="mapping",nullable=true,jdbcType=JdbcType.BIGINT,comment="映射关系")
	private Long mapping;
	
	/**
	 * 邀请者ID
	 */
	@Column(name="invitation",nullable=true,jdbcType=JdbcType.BIGINT,comment="邀请者ID")
	private Long invitation;
	
	/**
	 * 包名
	 */
	@Column(name="package_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
	/**
	 * 是否代理人邀请 0 不是 1 是
	 */
	@Column(name="proxy",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否代理人邀请 0 不是 1 是")
	private Integer proxy;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getMapping() {
		return mapping;
	}

	public void setMapping(Long mapping) {
		this.mapping = mapping;
	}
	
	public Long getInvitation() {
		return invitation;
	}

	public void setInvitation(Long invitation) {
		this.invitation = invitation;
	}
	
	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	
	public Integer getProxy() {
		return proxy;
	}

	public void setProxy(Integer proxy) {
		this.proxy = proxy;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
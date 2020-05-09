package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_user_invitecode] 表对应的实体类
 * @author yangjunming
 * @Date 2017-10-21 18:29:01
 *
 */
@Table(name="t_user_invitecode")
public class UserInvitecodeEntity extends BaseEntity implements Serializable {

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
	 * 邀请码
	 */
	@Column(name="inviteCode",nullable=true,jdbcType=JdbcType.VARCHAR,comment="邀请码")
	private String inviteCode;
	
	/**
	 * 状态 0 未使用 1 已使用
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态 0 未使用 1 已使用")
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
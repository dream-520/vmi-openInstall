package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户黑名单DTO
 * @author lipeng
 *
 */
public class UserBlacklistBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -489930027235111182L;

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 用户ID
	 */
	private Long userid;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 黑名单ID
	 */
	private Long blackid;
	
	/**
	 * 状态：0-正常 -9-删除
	 */
	private Integer status;

	
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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getBlackid() {
		return blackid;
	}

	public void setBlackid(Long blackid) {
		this.blackid = blackid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

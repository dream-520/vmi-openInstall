package com.tigerjoys.shark.miai.agent.dto;

import java.util.Date;

/**
 * 更全的用户信息对象
 * @author chengang
 *
 */
public class UserFullBO extends UserBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -587741117495520211L;
	
	/**
	 * 注册时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}

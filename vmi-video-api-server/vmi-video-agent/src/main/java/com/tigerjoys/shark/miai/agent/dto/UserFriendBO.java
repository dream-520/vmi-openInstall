package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户好友关系信息DTO
 * @author chengang
 *
 */
public class UserFriendBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4599182159127295665L;

	/**
	 * 关系ID
	 */
	private long id;
	
	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 好友ID
	 */
	private long friendid;
	
	/**
	 * 是否互粉,0否，1是
	 */
	private int eachFans;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getFriendid() {
		return friendid;
	}

	public void setFriendid(long friendid) {
		this.friendid = friendid;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getEachFans() {
		return eachFans;
	}

	public void setEachFans(int eachFans) {
		this.eachFans = eachFans;
	}

}

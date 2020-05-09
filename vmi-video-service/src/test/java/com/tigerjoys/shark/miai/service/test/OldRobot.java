package com.tigerjoys.shark.miai.service.test;

/**
 * 需要的老机器人的数据实体
 * @author shiming
 *
 */
public class OldRobot {
	
	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像(大图)
	 */
	private String photo;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	/**
	 * 用户最后登录设备号
	 */
	private String clientid;
	
	public OldRobot() {
		
	}
	
	public OldRobot(long userid, String nickname, String photo, String signature, String clientid) {
		this.userid = userid;
		this.nickname = nickname;
		this.photo = photo;
		this.signature = signature;
		this.clientid = clientid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
		
}

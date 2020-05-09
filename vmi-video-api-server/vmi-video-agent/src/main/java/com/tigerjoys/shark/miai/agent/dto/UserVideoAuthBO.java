package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视频认证信息DTO
 * @author chengang
 *
 */
public class UserVideoAuthBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5139540523688033167L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 用户ID
	 */
	private Long userid;
	
	/**
	 * 认证视频地址
	 */
	private String videoAuth;
	
	/**
	 * 认证视频展示图片地址
	 */
	private String videoAuthPic;
	
	/**
	 * 认证状态：1成功,2失败,0认证中
	 */
	private Integer status;
	
	/**
	 * 认证备注
	 */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getVideoAuth() {
		return videoAuth;
	}

	public void setVideoAuth(String videoAuth) {
		this.videoAuth = videoAuth;
	}

	public String getVideoAuthPic() {
		return videoAuthPic;
	}

	public void setVideoAuthPic(String videoAuthPic) {
		this.videoAuthPic = videoAuthPic;
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

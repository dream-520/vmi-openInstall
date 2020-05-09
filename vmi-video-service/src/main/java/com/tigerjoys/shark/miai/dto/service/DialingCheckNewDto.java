package com.tigerjoys.shark.miai.dto.service;

/**
 * 视频拨打前置判断
 * @author yangjunming
 *
 */
public class DialingCheckNewDto {
	
	/**
	 * 拨打状态  0 不能  1 可以拨打
	 */
	private int status;
	
	

	
	
	/**
	 * 视频通话网易用户ID
	 */
	private Long neteaseUserId;
	
	
	/**
	 * 跳转与弹窗
	 */
	private DlgAndGoPageNew dlgAndGoPage;
	
	
	/**
	 * 拨打状态  1余额不足去充值  2 小于2分钟去充值
	 */
	private int iOSTag;
	
	
	/**
	 * IOS提示
	 */
	private String iosDigInfo;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	public Long getNeteaseUserId() {
		return neteaseUserId;
	}

	public void setNeteaseUserId(Long neteaseUserId) {
		this.neteaseUserId = neteaseUserId;
	}

	public int getiOSTag() {
		return iOSTag;
	}

	public void setiOSTag(int iOSTag) {
		this.iOSTag = iOSTag;
	}


	public DlgAndGoPageNew getDlgAndGoPage() {
		return dlgAndGoPage;
	}

	public void setDlgAndGoPage(DlgAndGoPageNew dlgAndGoPage) {
		this.dlgAndGoPage = dlgAndGoPage;
	}

	public String getIosDigInfo() {
		return iosDigInfo;
	}

	public void setIosDigInfo(String iosDigInfo) {
		this.iosDigInfo = iosDigInfo;
	}

	
}

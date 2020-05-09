package com.tigerjoys.shark.miai.dto.service;

/**
 * 视频拨打前置判断
 * @author yangjunming
 *
 */
public class DialingCheckDto {
	
	/**
	 * 拨打状态  0 不能  1 可以拨打
	 */
	private int status;
	
	
	/**
	 * 拨打状态  1余额不足去充值  2 小于2分钟去充值
	 */
	private int iOSTag;
	
	
	
	/**
	 * 视频通话网易用户ID
	 */
	private Long neteaseUserId;
	
	
	/**
	 * 跳转与弹窗
	 */
	private DlgAndGoPage dlgAndGoPage;
	
	/**
	 * IOS提示
	 */
	private String dlgInfo;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public DlgAndGoPage getDlgAndGoPage() {
		return dlgAndGoPage;
	}

	public void setDlgAndGoPage(DlgAndGoPage dlgAndGoPage) {
		this.dlgAndGoPage = dlgAndGoPage;
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

	public String getDlgInfo() {
		return dlgInfo;
	}

	public void setDlgInfo(String dlgInfo) {
		this.dlgInfo = dlgInfo;
	}

	
}

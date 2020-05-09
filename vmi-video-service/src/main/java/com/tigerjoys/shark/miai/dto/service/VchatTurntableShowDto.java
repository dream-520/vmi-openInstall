package com.tigerjoys.shark.miai.dto.service;

/**
 * 转盘中状数据
 * 参数 otherId  对方ID
 */
public class VchatTurntableShowDto {
	
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 中奖号  从1开始  12点方向 为1  逆时针记数
	 * 如果是0 数明钱不够，或其它原因
	 * 为 0  客户端解析 dlgAndGoPage
	 * 如果不为0 ，转动结束之后再解析dlgAndGoPage
	 */
	private int  index;
	
	/**
	 * 弹窗信息
	 */
	private DlgAndGoPageNew dlgAndGoPage;

	
	/**
	 * IOS弹窗信息
	 */
	private String iosHintInfo;
	
	/**
	 * IOS弹窗信息  0 成功  1 去充值  
	 */
	private int iosTag;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public DlgAndGoPageNew getDlgAndGoPage() {
		return dlgAndGoPage;
	}

	public void setDlgAndGoPage(DlgAndGoPageNew dlgAndGoPage) {
		this.dlgAndGoPage = dlgAndGoPage;
	}

	public String getIosHintInfo() {
		return iosHintInfo;
	}

	public void setIosHintInfo(String iosHintInfo) {
		this.iosHintInfo = iosHintInfo;
	}

	public int getIosTag() {
		return iosTag;
	}

	public void setIosTag(int iosTag) {
		this.iosTag = iosTag;
	}
	
	
	
	
}

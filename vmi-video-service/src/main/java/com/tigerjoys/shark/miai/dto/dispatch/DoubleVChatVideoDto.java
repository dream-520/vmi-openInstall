package com.tigerjoys.shark.miai.dto.dispatch;

/**
 * 双人视频通话
 * @author yangjunming
 *
 */
public class DoubleVChatVideoDto {
	/**
     * 对方用户id
     */
    private long otherId;

    /**
     * 对方用户名字
     */
    private String otherName;

    /**
     * 对方用户头像
     */
    private String otherHeadUrl;

    /**
     * 等待界面中的收支信息（全部文字）
     */
    private String incomePayInfo;

    /**
     * 对方余额 （全部文字）
     */
    private String otherBalance;

    /**
     * 是否是卖方
     */
    private boolean seller;

    /**
     * 房间号
     */
    private long roomId;
    
    /**
     * 通话单号
     */
    private long orderId;
    
    /**
     * 对方余额
     */
    private String info1;
    
    /**
     * 上次通话信息
     */
    private String info2;
    
    
    /**
     * 显示关注 0不显示 1显示
     */
    private int showFollow;
    
   
    /**
     * 显示gif蒙层 0不显示 大于1显示不通的GIF图
     */
   // private int showGifMongolia;
    
    /**
     * 显示蒙层地址
     */
    private String maskLayerUrl;


    /**
     * 蒙层 文字
     */
    private String mongoliaText;
    
    /**
     * 普通用户等级
     */
    private String normalLevel;
    
    
    /**
     * 用户是否关灯
     * 0不关灯 1关灯
     */
    private int turnOffLight;
    
    /**
     * vip  0 没 1有
     */
    private int vip;
    
    /**
     * 守护图标
     */
    private String guardIcon;


    /**
     * 自定义
     */
    private String custom;
    
    /**
     * 主播视频地址
     */
    private String playVideoUrl;

	public long getOtherId() {
		return otherId;
	}

	public void setOtherId(long otherId) {
		this.otherId = otherId;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getOtherHeadUrl() {
		return otherHeadUrl;
	}

	public void setOtherHeadUrl(String otherHeadUrl) {
		this.otherHeadUrl = otherHeadUrl;
	}

	public String getIncomePayInfo() {
		return incomePayInfo;
	}

	public void setIncomePayInfo(String incomePayInfo) {
		this.incomePayInfo = incomePayInfo;
	}

	public String getOtherBalance() {
		return otherBalance;
	}

	public void setOtherBalance(String otherBalance) {
		this.otherBalance = otherBalance;
	}

	public boolean isSeller() {
		return seller;
	}

	public void setSeller(boolean seller) {
		this.seller = seller;
	}


	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public int getShowFollow() {
		return showFollow;
	}

	public void setShowFollow(int showFollow) {
		this.showFollow = showFollow;
	}


	public String getMongoliaText() {
		return mongoliaText;
	}

	public void setMongoliaText(String mongoliaText) {
		this.mongoliaText = mongoliaText;
	}

	public String getMaskLayerUrl() {
		return maskLayerUrl;
	}

	public void setMaskLayerUrl(String maskLayerUrl) {
		this.maskLayerUrl = maskLayerUrl;
	}

	public String getNormalLevel() {
		return normalLevel;
	}

	public void setNormalLevel(String normalLevel) {
		this.normalLevel = normalLevel;
	}

	public int getTurnOffLight() {
		return turnOffLight;
	}

	public void setTurnOffLight(int turnOffLight) {
		this.turnOffLight = turnOffLight;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public String getGuardIcon() {
		return guardIcon;
	}

	public void setGuardIcon(String guardIcon) {
		this.guardIcon = guardIcon;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getPlayVideoUrl() {
		return playVideoUrl;
	}

	public void setPlayVideoUrl(String playVideoUrl) {
		this.playVideoUrl = playVideoUrl;
	}
	
}

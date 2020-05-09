package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.dto.index.GotoDataItem;

/**
 * 首页- banner和活动专区 子项数据
 * @author liuman
 */

public class VchatturntablePayDto implements Serializable {
	
	private static final long serialVersionUID = -4173301968762634123L;
	/**
	 * 订单ID
	 */
	private String orderId; 
	/**
	 * 用户ID
	 */
    private long userId; 
    /**
     * 对方ID
     */
    private long otherId; 
    /**
     * 转盘ID
     */
    private long turntableId;
    
    /**
     * 主播显示信息
     */
    private String anchorShowInfo;


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOtherId() {
		return otherId;
	}

	public void setOtherId(long otherId) {
		this.otherId = otherId;
	}

	public long getTurntableId() {
		return turntableId;
	}

	public void setTurntableId(long turntableId) {
		this.turntableId = turntableId;
	}

	public String getAnchorShowInfo() {
		return anchorShowInfo;
	}

	public void setAnchorShowInfo(String anchorShowInfo) {
		this.anchorShowInfo = anchorShowInfo;
	}
    
    
}

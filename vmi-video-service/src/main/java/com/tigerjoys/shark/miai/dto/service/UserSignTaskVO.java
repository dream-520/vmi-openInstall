package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;
import java.util.List;

import com.tigerjoys.shark.miai.dto.index.GotoDataItem;

/**
 * 用户资料详情dto
 * @author lipeng
 *
 */
public class UserSignTaskVO implements Serializable {
	

	 /**
	 * 
	 */
	private static final long serialVersionUID = 3730355551443776410L;
	/**
     * 累计积分
     */
    private String totalIntegral;
    /**
     * 当前积分
     */
    private String currentIntegral;
    /**
     * 签到积分列表
     */
    private List<String> integral;
    /**
     * 今天签到状态 1签到完成 0未签到
     */
    private int status;
	 /**
     * 已签到第几天
     */
    private int signDays;
    /**
     * 积分任务列表
     */
    private List<TaskItemDto> taskData;
    
    /**
     * banner数据
     */
    private List<GotoDataItem> banners;
    /**
     * 连续签到xx天
     */
    private String signTip;
    
	public String getTotalIntegral() {
		return totalIntegral;
	}
	public void setTotalIntegral(String totalIntegral) {
		this.totalIntegral = totalIntegral;
	}
	public String getCurrentIntegral() {
		return currentIntegral;
	}
	public void setCurrentIntegral(String currentIntegral) {
		this.currentIntegral = currentIntegral;
	}
	public List<String> getIntegral() {
		return integral;
	}
	public void setIntegral(List<String> integral) {
		this.integral = integral;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSignDays() {
		return signDays;
	}
	public void setSignDays(int signDays) {
		this.signDays = signDays;
	}
	public List<TaskItemDto> getTaskData() {
		return taskData;
	}
	public void setTaskData(List<TaskItemDto> taskData) {
		this.taskData = taskData;
	}
	public List<GotoDataItem> getBanners() {
		return banners;
	}
	public void setBanners(List<GotoDataItem> banners) {
		this.banners = banners;
	}
	public String getSignTip() {
		return signTip;
	}
	public void setSignTip(String signTip) {
		this.signTip = signTip;
	}
    
}

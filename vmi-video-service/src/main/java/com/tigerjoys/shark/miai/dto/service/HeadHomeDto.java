package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

import org.shark.miai.common.dto.IdItemDto;

import com.tigerjoys.shark.miai.dto.index.GotoDataItem;

/**
 * 首页数据
 * @author yangjunming
 *
 */
public class HeadHomeDto {
	/**
     * 派单类别列表
     */
    private List<IdItemDto> dtoList;

    /**
     * 头条数据列表
     */
    private List<TopLineHeadDto> topLineList;

    /**
     * 活动数据列表
     */
    private List<GotoDataItem> activityList;

    /**
     * 推荐达人数据列表
     */
    private List<RecommendHeadDto> recommendMasterList;

    /**
     * 派单推荐列表倒计时 毫秒
     */
    private long countDownTime;

	public List<IdItemDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<IdItemDto> dtoList) {
		this.dtoList = dtoList;
	}

	public List<TopLineHeadDto> getTopLineList() {
		return topLineList;
	}

	public void setTopLineList(List<TopLineHeadDto> topLineList) {
		this.topLineList = topLineList;
	}


	public List<GotoDataItem> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<GotoDataItem> activityList) {
		this.activityList = activityList;
	}

	public List<RecommendHeadDto> getRecommendMasterList() {
		return recommendMasterList;
	}

	public void setRecommendMasterList(List<RecommendHeadDto> recommendMasterList) {
		this.recommendMasterList = recommendMasterList;
	}

	public long getCountDownTime() {
		return countDownTime;
	}

	public void setCountDownTime(long countDownTime) {
		this.countDownTime = countDownTime;
	}

	
    
    
	
}

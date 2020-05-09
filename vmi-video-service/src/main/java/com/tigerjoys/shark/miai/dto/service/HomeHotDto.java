package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

import org.shark.miai.common.dto.IdItemDto;

import com.tigerjoys.shark.miai.dto.index.GotoDataItem;

/**
 * 首页数据
 * @author yangjunming
 *
 */
public class HomeHotDto {
	/**
     * 派单类别列表
     */
    private List<HotTypeItemDto> dtoList;

    /**
     * 头条数据列表
     */
    private List<TopLineHeadDto> topLineList;

    /**
     * 派单推荐列表倒计时 毫秒
     */
    private long countDownTime;

	public List<HotTypeItemDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<HotTypeItemDto> dtoList) {
		this.dtoList = dtoList;
	}

	public List<TopLineHeadDto> getTopLineList() {
		return topLineList;
	}

	public void setTopLineList(List<TopLineHeadDto> topLineList) {
		this.topLineList = topLineList;
	}


	public long getCountDownTime() {
		return countDownTime;
	}

	public void setCountDownTime(long countDownTime) {
		this.countDownTime = countDownTime;
	}

	
    
    
	
}

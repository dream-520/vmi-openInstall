package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

import com.tigerjoys.shark.miai.dto.index.BannerData;

/**
 * B包首页数据
 * @author yangjunming
 *
 */
public class HomeBDto {
	/**
     * 派单类别列表
     */
	private BannerBData bannerData; //banner

    /**
     * 头条数据列表
     */
    private List<TopLineHeadDto> topLineList;



	public List<TopLineHeadDto> getTopLineList() {
		return topLineList;
	}

	public void setTopLineList(List<TopLineHeadDto> topLineList) {
		this.topLineList = topLineList;
	}

	public BannerBData getBannerData() {
		return bannerData;
	}

	public void setBannerData(BannerBData bannerData) {
		this.bannerData = bannerData;
	}
    
    

}

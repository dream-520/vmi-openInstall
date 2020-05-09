package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;
import java.util.List;

import com.tigerjoys.shark.miai.dto.index.GotoDataItem;

/**
 * Banner录播图数据
 * @author liuman
 */

public class BannerBData implements Serializable {
	
	private static final long serialVersionUID = -8248815564181367611L;
	
	/**
	 * 轮播图列表数据
	 */
	private List<GotoDataItem> banners;
	
	/**
	 * 首页关键区域
	 */
	private List<GotoDataItem> tabList;
	
	/**
	 * 悬浮
	 */
	private GotoDataItem suspension;
	
	/**
	 * 播放间隔时间
	 */
    private int time; //秒

    public List<GotoDataItem> getBanners() {
        return banners;
    }

    public void setBanners(List<GotoDataItem> banners) {
        this.banners = banners;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

	public List<GotoDataItem> getTabList() {
		return tabList;
	}

	public void setTabList(List<GotoDataItem> tabList) {
		this.tabList = tabList;
	}

	public GotoDataItem getSuspension() {
		return suspension;
	}

	public void setSuspension(GotoDataItem suspension) {
		this.suspension = suspension;
	}
    
}

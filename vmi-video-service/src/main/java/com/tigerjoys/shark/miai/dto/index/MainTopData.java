package com.tigerjoys.shark.miai.dto.index;

import java.io.Serializable;
import java.util.List;

/**
 * 首页顶部数据(轮播、活动专区和热门分类)
 * @author liuman
 */

public class MainTopData implements Serializable {
	
	private static final long serialVersionUID = -6509857714742999952L;
	
	private BannerData bannerData; //banner
    private List<GotoDataItem> activities; //活动
    private List<MasterDataItem> masterData; //热门分类

    public BannerData getBannerData() {
        return bannerData;
    }

    public void setBannerData(BannerData bannerData) {
        this.bannerData = bannerData;
    }

    public List<GotoDataItem> getActivities() {
        return activities;
    }

    public void setActivities(List<GotoDataItem> activities) {
        this.activities = activities;
    }

    public List<MasterDataItem> getMasterData() {
        return masterData;
    }

    public void setMasterData(List<MasterDataItem> masterData) {
        this.masterData = masterData;
    }
}

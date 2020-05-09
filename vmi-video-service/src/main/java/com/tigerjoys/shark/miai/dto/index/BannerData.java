package com.tigerjoys.shark.miai.dto.index;

import java.io.Serializable;
import java.util.List;

/**
 * Banner录播图数据
 * @author liuman
 */

public class BannerData implements Serializable {
	
	private static final long serialVersionUID = -8248815564181367611L;
	
	/**
	 * 轮播图列表数据
	 */
	private List<GotoDataItem> banners;
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
}

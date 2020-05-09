package com.tigerjoys.shark.miai.dto.index;

import java.io.Serializable;

/**
 * 首页- banner和活动专区 子项数据
 * @author liuman
 */

public class GotoDataItem implements Serializable {
	
	private static final long serialVersionUID = -4173301968762634123L;
	
	private String imageurl; //图片地址
    private String androidPage; //安卓内页名
    private String iosPage; //安卓内页名
    private String parame; //应用内链接需要的参数
    private String clickEvent;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getAndroidPage() {
        return androidPage;
    }

    public void setAndroidPage(String androidPage) {
        this.androidPage = androidPage;
    }

    public String getIosPage() {
		return iosPage;
	}

	public void setIosPage(String iosPage) {
		this.iosPage = iosPage;
	}

	public String getParame() {
        return parame;
    }

    public void setParame(String parame) {
        this.parame = parame;
    }

    public String getClickEvent() {
        return clickEvent;
    }

    public void setClickEvent(String clickEvent) {
        this.clickEvent = clickEvent;
    }
}

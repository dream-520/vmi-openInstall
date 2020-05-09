package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.dto.index.GotoDataItem;

/**
 * 首页- banner和活动专区 子项数据
 * @author liuman
 */

public class ActivityH5Dto implements Serializable {
	
	private static final long serialVersionUID = -4173301968762634123L;
	
	private String imageurl; //图片地址
    private String appPage; //APP内页名
    private String parame; //应用内链接需要的参数
    private String clickEvent;

    public  static ActivityH5Dto pareDto(GotoDataItem entity,int type){
    	ActivityH5Dto dto=new ActivityH5Dto();
    	dto.setImageurl(entity.getImageurl());
    	dto.setAppPage(type == 1 ? entity.getAndroidPage():entity.getIosPage());
    	dto.setParame(Tools.isNotNull(entity.getParame())?entity.getParame():"{}");
    	//dto.setClickEvent("window.target.gotoAppPage('"++"')");
    	return dto;
    }
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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
	public String getAppPage() {
		return appPage;
	}
	public void setAppPage(String appPage) {
		this.appPage = appPage;
	}
    
    
}

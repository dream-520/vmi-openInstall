package com.tigerjoys.shark.miai.dto.index;

import java.io.Serializable;

/**
 * 首页- 热门分类子项数据
 * @author liuman
 */

public class MasterDataItem implements Serializable {
	
	private static final long serialVersionUID = -1653383327797165753L;
	
	private String imageurl; //图片地址
    private int masterType; //达人秀类型

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getMasterType() {
        return masterType;
    }

    public void setMasterType(int masterType) {
        this.masterType = masterType;
    }
}

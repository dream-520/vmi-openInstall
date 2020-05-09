package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于区分动态类型的枚举
 * @author shiming
 *
 */
public enum AppointSiteTypeEnum {
	//图片
	feather(1,"羽毛",""),
	fitness(2,"健身",""),
	basketball(3,"篮球",""),
	tennis(4,"网球",""),
	yoga(5,"瑜伽",""),
	swimming(6,"游泳",""),
	Table_tennis(7,"乒乓球",""),
	dance(8,"舞蹈",""),
 	;

	private int type;
	private String desc;
	private String url;

	private static final Map<Integer, AppointSiteTypeEnum> TYPE_DESC = new HashMap<Integer, AppointSiteTypeEnum>();

	static {
		for (AppointSiteTypeEnum refer : AppointSiteTypeEnum.values()) {
			TYPE_DESC.put(refer.getType(), refer);
		}
	}

	private AppointSiteTypeEnum(int type, String desc, String url) {
		this.type = type;
		this.desc = desc;
		this.url = url;
	}

	public static AppointSiteTypeEnum getByCode(int type) {
		AppointSiteTypeEnum errCode = TYPE_DESC.get(type);
		if (errCode == null) {
			return null;
		}
		return errCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

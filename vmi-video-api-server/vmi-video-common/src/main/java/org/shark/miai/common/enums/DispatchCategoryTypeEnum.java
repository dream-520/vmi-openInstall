package org.shark.miai.common.enums;

/**
 * 派单类型的种类
 * @author liuman
 *
 */
public enum DispatchCategoryTypeEnum {
	
	ordinary(0,"普通"),
	custom(1, "自定义"),
    ;
	
	private int code;
	private String desc;
	
	private DispatchCategoryTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (DispatchCategoryTypeEnum refer : DispatchCategoryTypeEnum.values())
			if (code == refer.getCode())
				return refer.getDesc();
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}

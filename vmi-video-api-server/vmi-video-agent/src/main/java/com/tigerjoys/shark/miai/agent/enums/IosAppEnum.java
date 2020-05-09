package com.tigerjoys.shark.miai.agent.enums;

/**
 * ios的app页面类型枚举
 * @author liuman
 *
 */
public enum IosAppEnum {
	
	bussiness_page("1","业务消息列表"),
	my_publish("2","我发布的"),
	my_purchase("3","我购买的"),
	;
	
	private String code;
	private String desc;

	private IosAppEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}

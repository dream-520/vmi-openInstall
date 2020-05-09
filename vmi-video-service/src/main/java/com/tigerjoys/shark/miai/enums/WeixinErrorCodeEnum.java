package com.tigerjoys.shark.miai.enums;

import java.util.Map;

import com.google.common.collect.Maps;
import com.tigerjoys.nbs.common.IErrorCodeEnum;
import com.tigerjoys.nbs.common.utils.Tools;

public enum WeixinErrorCodeEnum implements IErrorCodeEnum {
	
	SUCCESS(0, "成功"),
	REDIRECT_URL_ERROR(10003, "redirect_uri域名与后台配置不一致"),
	SEALUP(10004, "此公众号被封禁"),
	INSUFFICIENT_AUTHORITY(10005, "此公众号并没有这些scope的权限"),
	FOLLOW_TEST_ACCOUNT(10006, "必须关注此测试号"),
	TO_OFTEN(10009, "操作太频繁了，请稍后重试"),
	SCOPE_EMPTY(10010, "scope不能为空"),
	REDIRECT_URL_EMPTY(10011, "redirect_uri不能为空"),
	APPID_EMPTY(10012, "appid不能为空"),
	STATE_EMPTY(10013, "state不能为空"),
	NO_AUTHORIZE(10015, "公众号未授权第三方平台，请检查授权状态"),
	NO_SUPPORT(10016, "不支持微信开放平台的Appid，请使用公众号Appid"),
	;
	
	private static final Map<Integer , WeixinErrorCodeEnum> ERR_DESC = Maps.newHashMap();
	
	static {
		for(WeixinErrorCodeEnum refer : WeixinErrorCodeEnum.values()) {
			ERR_DESC.put(refer.getCode(), refer);
		}
	}
	
	private final int code;
	private final String desc;
	
	private WeixinErrorCodeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static WeixinErrorCodeEnum getByCode(int code) {
		return ERR_DESC.get(code);
	}

	public static String getDescByCode(int code) {
		WeixinErrorCodeEnum e = getByCode(code);
		
		return e != null ? e.desc : Tools.EMPTY_STRING;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}

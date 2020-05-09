package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送枚举
 * @author lipeng
 *
 */
public enum AlibabaSmsTypeEnum {
	
	custom(0 , "自定义短信"),
	regist(1 , "注册发送验证码"),
	forget_pass(2 , "忘记密码发送验证码"),
	auth_mobile(3 , "验证联系方式"),
	
	paid_appoint_timer_warn(999 , "付费约定时任务告警"),
 	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(AlibabaSmsTypeEnum refer : AlibabaSmsTypeEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private AlibabaSmsTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
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

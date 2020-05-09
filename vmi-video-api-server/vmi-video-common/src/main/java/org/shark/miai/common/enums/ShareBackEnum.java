package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 分享内容到朋友圈或者qq空间的回调枚举类
 * @author liuman
 *
 */
public enum ShareBackEnum {
	
	success(0,"分享成功"),
	fail(1,"分享失败"),
 	;
	
	/**
	 * 分享回调结果代码
	 */
	private int code;
	/**
	 * 任务描述
	 */
	private String desc;
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(ShareBackEnum refer : ShareBackEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private ShareBackEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getCodeByType(int code) {
		return code_desc.get(code);
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

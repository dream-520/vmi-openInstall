package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理人收益流水状态
 * @author yangjunming
 *
 */
public enum ProxyTransStatusEnum {
	
	income(1,"收入"),
	rollback(2,"退款"),
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
		for(ProxyTransStatusEnum refer : ProxyTransStatusEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private ProxyTransStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
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

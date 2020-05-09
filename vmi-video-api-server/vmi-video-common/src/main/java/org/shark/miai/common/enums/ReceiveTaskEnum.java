package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务按钮状态枚举
 * @author liuman
 *
 */
public enum ReceiveTaskEnum {
	
	not_finish(0,"未完成"),
	finish_not_receive(1,"领取"),
	finish_receive(2,"已领取"),
 	;
	
	/**
	 * 领取任务代码
	 */
	private int code;
	/**
	 * 领取任务按钮描述
	 */
	private String desc;
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	
	static {
		for(ReceiveTaskEnum refer : ReceiveTaskEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private ReceiveTaskEnum(int code, String desc) {
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

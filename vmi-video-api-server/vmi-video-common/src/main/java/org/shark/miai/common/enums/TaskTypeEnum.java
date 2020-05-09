package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务类型枚举
 * @author liuman
 *
 */
public enum TaskTypeEnum {
	
	novice_task(11,"noviceTask","新手任务"),
	everyday_task(12,"everydayTask","每日任务"),
 	;
	
	/**
	 * 任务类型
	 */
	private int type;
	/**
	 * 任务代码
	 */
	private String code;
	/**
	 * 任务描述
	 */
	private String desc;
	
	private static final Map<Integer , String> type_desc = new HashMap<Integer , String>();
	
	private static final Map<Integer , String> type_code = new HashMap<Integer , String>();
	
	static {
		for(TaskTypeEnum refer : TaskTypeEnum.values()) {
			type_desc.put(refer.getType(), refer.getDesc());
			type_code.put(refer.getType(), refer.getCode());
		}
	}
	
	private TaskTypeEnum(int type, String code, String desc) {
		this.type = type;
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByType(int type) {
		return type_desc.get(type);
	}
	
	public static String getCodeByType(int type) {
		return type_code.get(type);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

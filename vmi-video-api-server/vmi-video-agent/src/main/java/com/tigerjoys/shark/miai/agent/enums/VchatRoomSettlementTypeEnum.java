/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.utils.Tools;

/** 
 * 
 * @author yangjunming 
 * @version  
 * @since JDK 1.8.0 
 */
public enum VchatRoomSettlementTypeEnum {

	minute(0,"分钟"),
	second(1,"10S"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , VchatRoomSettlementTypeEnum> err_desc = new HashMap<Integer , VchatRoomSettlementTypeEnum>();
	
	static {
		for(VchatRoomSettlementTypeEnum refer : VchatRoomSettlementTypeEnum.values()) {
			err_desc.put(refer.getCode(), refer);
		}
	}
	
	private VchatRoomSettlementTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static VchatRoomSettlementTypeEnum getByCode(int code) {
		return err_desc.get(code);
	}
	
	public static String getDescByCode(int code) {
		VchatRoomSettlementTypeEnum type = err_desc.get(code);
		return Tools.isNull(type)?"":type.getDesc();
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

/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: AccountLogTypeEnum <br/> 
 * date: 2017年5月10日 下午7:04:44 <br/> 
 * 
 * @author yangjunming 
 * @version  
 * @since JDK 1.8.0 
 */
public enum VchatRoomFreeIncomeFalgEnum {
	no_income(0,"无收益"), //无收益
	not_recorded(1,"待结算"),
	anchor_close_no_income(2,"无收益"),
	income_recorded(3,"已入账"),
	
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(VchatRoomFreeIncomeFalgEnum refer : VchatRoomFreeIncomeFalgEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private VchatRoomFreeIncomeFalgEnum(int code, String desc) {
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

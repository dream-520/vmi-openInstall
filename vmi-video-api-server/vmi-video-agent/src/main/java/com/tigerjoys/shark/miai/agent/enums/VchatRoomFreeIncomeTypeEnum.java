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
public enum VchatRoomFreeIncomeTypeEnum {
	free_old_income(0,"原始方案:30以下1元，以上6元结算"),
	free_new_income(1,"新奖励:30以下0元，以上给固定1元"),
	no_income(2,"无奖励"), //无收益
	free_new2_income(3,"主播扣费控制加个30s一下0.5元，以上1元"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , VchatRoomFreeIncomeTypeEnum> err_desc = new HashMap<Integer , VchatRoomFreeIncomeTypeEnum>();
	
	static {
		for(VchatRoomFreeIncomeTypeEnum refer : VchatRoomFreeIncomeTypeEnum.values()) {
			err_desc.put(refer.getCode(), refer);
		}
	}
	
	private VchatRoomFreeIncomeTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static VchatRoomFreeIncomeTypeEnum getByCode(int code) {
		return err_desc.get(code);
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code).getDesc();
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

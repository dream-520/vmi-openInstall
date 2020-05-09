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
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum RechargeOrderDonorTimesEnum {

	first(0,"首次"),
	every(1, "每次"),
	nothing(2, "无"),
	repetition_desc(3, "复充"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(RechargeOrderDonorTimesEnum refer : RechargeOrderDonorTimesEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private RechargeOrderDonorTimesEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
	}
	
	public static RechargeOrderDonorTimesEnum getByCode(int code) {
		for (RechargeOrderDonorTimesEnum refer : RechargeOrderDonorTimesEnum.values())
			if (code == refer.getCode())
				return refer;
		return null;
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

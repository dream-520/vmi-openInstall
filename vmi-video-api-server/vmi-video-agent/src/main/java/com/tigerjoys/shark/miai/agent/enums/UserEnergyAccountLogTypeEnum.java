/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: UserEnergyAccountLogTypeEnum <br/>
 * date: 2017年5月10日 下午7:04:44 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum UserEnergyAccountLogTypeEnum {

	unknown(0,"未知"),
	recharge_account(1, "账户充值"),
	pay_vip(2, "购买VIP"),
	check_user(3, "付费查看信息"),
	;

	private int code;
	private String desc;

	private static final Map<Integer , String> descs = new HashMap<Integer , String>();

	static {
		for(UserEnergyAccountLogTypeEnum refer : UserEnergyAccountLogTypeEnum.values()) {
			descs.put(refer.getCode(), refer.getDesc());
		}
	}

	private UserEnergyAccountLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return descs.get(code);
	}
	
	public static UserEnergyAccountLogTypeEnum getByCode(int code) {
		for (UserEnergyAccountLogTypeEnum refer : UserEnergyAccountLogTypeEnum.values())
			if (code == refer.getCode())
				return refer;
		return unknown;
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

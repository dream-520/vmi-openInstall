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
public enum AccountLogTypeEnum {

	unknown(0,"未知"),
	recharge_account(1, "账户充值"),
	consume_account(2, "账户消费"),
	withdraw_account(3,"账户提现"),
	finiashtask_account(4,"领取任务增加现金"),
	buy_prop(5 , "购买道具"),
	reimburse(6 , "提现退款"),
	buy_vip(7 , "购买VIP"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(AccountLogTypeEnum refer : AccountLogTypeEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private AccountLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
	}
	
	public static AccountLogTypeEnum getByCode(int code) {
		for (AccountLogTypeEnum refer : AccountLogTypeEnum.values())
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

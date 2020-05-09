package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: PayTypeEnum <br/> 
 * date: 2017年8月14日 下午7:03:34 <br/> 
 *  支付种类枚举
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum PayChannelEnum {

	ali(1,"支付宝快充"),
	wx(2,"微信快充"),
	balance(3,"余额支付"),
	iap(4,"苹果支付"),
	wxH5(5,"微信H5支付"),
	aliH5(6,"支付宝H5支付"),
	wxJS(7, "微信JS支付"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer,PayChannelEnum> pc = new HashMap<Integer,PayChannelEnum>();
	
	static {
		for(PayChannelEnum refer : PayChannelEnum.values()) {
			pc.put(refer.getCode(), refer);
		}
	}
	
	private PayChannelEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static PayChannelEnum getByCode(int code) {
		PayChannelEnum errCode = pc.get(code);
		if(errCode == null) {
			return null;
		}
		return errCode;
	}
	
	public static String getDescByCode(int code) {
		return getByCode(code).desc;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
}

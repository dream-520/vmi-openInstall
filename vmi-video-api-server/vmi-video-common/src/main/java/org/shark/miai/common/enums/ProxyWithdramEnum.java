package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理人结算类型枚举
 * @author yangjunming
 *
 */
public enum ProxyWithdramEnum {
	
	alibaba(0,"支付宝"),
	tencent(1,"微信"),
	bank(2,"银行"),
 	;
	
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 类型描述
	 */
	private String desc;
	
	private ProxyWithdramEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(ProxyWithdramEnum refer : ProxyWithdramEnum.values()) {
			code_desc.put(refer.getType(), refer.getDesc());
		}
	}
	
	public static String getDescByCode(int code) {
		return code_desc.get(code);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}

/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * 
 * @author yangjunming 
 * @version  
 * @since JDK 1.8.0 
 */
public enum UserChatGiftBoxLogTypeEnum {

	
	unknown(0,"未知",BoxLogIOEnum.CONSUME),
	anchor_presented(1, "主播赠送",BoxLogIOEnum.INCOME),
	user_presented(2,"用户赠送",BoxLogIOEnum.CONSUME),
	;

	
	/**
	 * 日志类型
	 */
	private int code;
	
	/**
	 * 描述
	 */
	private String desc;
	
	/**
	 *  0 消费 1 收入
	 */
	private BoxLogIOEnum io;
	
	private static final Map<Integer , String> descs = new HashMap<Integer , String>();
	
	static {
		for(UserChatGiftBoxLogTypeEnum refer : UserChatGiftBoxLogTypeEnum.values()) {
			descs.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserChatGiftBoxLogTypeEnum(int code, String desc,BoxLogIOEnum io) {
		this.code = code;
		this.desc = desc;
		this.io = io;
	}
	
	public static String getDescByCode(int code) {
		return descs.get(code);
	}
	
	public static UserChatGiftBoxLogTypeEnum getByCode(int code) {
		for (UserChatGiftBoxLogTypeEnum refer : UserChatGiftBoxLogTypeEnum.values())
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
	
	
	
	public BoxLogIOEnum getIo() {
		return io;
	}

	public void setIo(BoxLogIOEnum io) {
		this.io = io;
	}



	//收益类型
	public enum BoxLogIOEnum{
		
		INCOME(1,"收入"),
		CONSUME(0,"消费"),
		;
		private int code;
		private String desc;
		
		private BoxLogIOEnum(int code ,String desc){
			this.code = code;
			this.desc = desc;
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
}

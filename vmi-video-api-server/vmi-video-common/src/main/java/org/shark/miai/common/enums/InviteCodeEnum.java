package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 代金券来源组
 * @author yangjunming
 *
 */
public enum InviteCodeEnum {
	
	illegal(1,"邀请码不合法"),
	noExist(2,"邀请码不存在"),
	Used(3,"邀请码已填写"),
	self_invite_self(4,"自己不能邀请自己"),
	
 	;
	
	/**
	 * 结果码
	 */
	private int code;
	/**
	 * 类型描述
	 */
	private String desc;
	
	private InviteCodeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(InviteCodeEnum refer : InviteCodeEnum.values()) {
			code_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	public static String getDescByCode(int code) {
		return code_desc.get(code);
	}
	

	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public static Map<Integer, String> getCodeDesc() {
		return code_desc;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}

package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 代金券来源组
 * @author yangjunming
 *
 */
public enum CouponGroupEnum {
	
	new_user(0,"新会员"),
	change(1,"充值"),
	invitation(2,"邀请"),
	talent_vip(3,"达人VIP"),
	lucky_draw(4,"幸运抽奖"),
	offline_ktv(5,"线下KTV"),
 	;
	
	/**
	 * 类型
	 */
	private int group;
	/**
	 * 类型描述
	 */
	private String desc;
	
	private CouponGroupEnum(int group, String desc) {
		this.group = group;
		this.desc = desc;
	}
	
	private static final Map<Integer , String> code_desc = new HashMap<Integer , String>();
	
	static {
		for(CouponGroupEnum refer : CouponGroupEnum.values()) {
			code_desc.put(refer.getGroup(), refer.getDesc());
		}
	}
	
	public static String getDescByCode(int code) {
		return code_desc.get(code);
	}
	
	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}

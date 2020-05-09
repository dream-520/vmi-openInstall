/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: UserPointAccountLogTypeEnum <br/> 
 * date: 2017年5月10日 下午7:04:44 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public enum UserPointAccountLogTypeEnum {

	unknown(0,"未知"),
	user_sign(1, "用户签到"),
	bind_mobile(2 , "绑定手机号"),
	invite_friends(3 , "邀请好友"),
	video_chat(4 , "视频通话不少于3分钟"),
	audio_chat(5, "语音通话不少于3分钟"),
	send_gifts(6, "送礼物"),
	recharge_limit_award(7, "充值不少于100"),
	goddess_star_vote(8, "参加女神之星投票活动"),
	point_transform_diamond(98, "积分换钻"),
	point_transform_income(99, "积分换收益"),
	//不要超过这个值
	test(100 , "测试"),
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> descs = new HashMap<Integer , String>();
	
	static {
		for(UserPointAccountLogTypeEnum refer : UserPointAccountLogTypeEnum.values()) {
			descs.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private UserPointAccountLogTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return descs.get(code);
	}
	
	public static UserPointAccountLogTypeEnum getByCode(int code) {
		for (UserPointAccountLogTypeEnum refer : UserPointAccountLogTypeEnum.values())
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

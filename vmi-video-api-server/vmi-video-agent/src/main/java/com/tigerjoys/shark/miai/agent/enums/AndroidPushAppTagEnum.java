package com.tigerjoys.shark.miai.agent.enums;


/**
 * app页参数类
 * @author liuman
 */
public enum AndroidPushAppTagEnum {
	
	has_attend_user("MyFriendFragment", 1, "关注用户"),
	sysmessage_H5_fragment("WebSingleSaveFragment", 2, "系统消息推送"),
	currency_H5_fragment("WebSingleSaveFragment",3,"通用消息推送"),
	bussiness_msg_fragment("BusMsgFragment",4,"业务消息推送"),
	examine_publish_service("BusMsgFragment",5,"投诉成功消息推送"),
	successful_complaint("BusMsgFragment",6,"投诉失败消息推送"),
	failed_complaint("BusMsgFragment",7,"投诉成功消息推送"),
	disable_servicer_vip("BusMsgFragment",8,"禁止达人vip功能的服务消息推送"),
	certificationPassed("BusMsgFragment",9,"审核服务者发布的服务消息推送"),
	;
	
	private String code;
	private int type;
	private String desc;

	private AndroidPushAppTagEnum(String code, int type, String desc) {
		this.type = type;
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(String code) {
		if(code == null) return null;
		for (AndroidPushAppTagEnum refer : AndroidPushAppTagEnum.values())
			if (code.equals(refer.getCode()))
				return refer.getDesc();
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

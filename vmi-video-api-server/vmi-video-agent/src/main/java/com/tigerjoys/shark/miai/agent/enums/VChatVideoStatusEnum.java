package com.tigerjoys.shark.miai.agent.enums;

/**
 * 提現状态类型
 * @author chengang
 *
 */
public enum VChatVideoStatusEnum {
	//
	answer(1,"接听"),
	enter_room(2, "进入房间"),
	exit(3 , "退出"),
	user_balance(4 , "用户余额"),
	camera_status(5 , "摄像头状态"),
	close_obscuration(6 , "关闭蒙层"),
	anchor_turntable_info(7 , "主播转盘显示"),
	info_notify(8 , "信息通告"),
	check_Porn(9,"监黄"),
	audio_msg_fail(10,"语音消息发送失败提示"),
	
    ;
	
	private int code;
	private String desc;
	
	private VChatVideoStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (VChatVideoStatusEnum refer : VChatVideoStatusEnum.values()) {
			if (code == refer.getCode()) {
				return refer.getDesc();
			}
		}
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

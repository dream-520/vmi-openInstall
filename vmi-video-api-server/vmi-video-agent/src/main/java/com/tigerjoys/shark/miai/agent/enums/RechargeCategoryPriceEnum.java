/**
 * 
 */
package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * ClassName: AccountLogTypeEnum <br/> 
 * 
 * @author yangjunming 
 * @version  
 * @since JDK 1.8.0 
 */
public enum RechargeCategoryPriceEnum {

	video(0,"视频"),
	audio(1, "音频"),
	video_ipa(2, "视频IPA"),
	audio_ipa(3,"音频IPA"),
	red_flower(4,"小红花"),
	guard(5,"守护"),
	VIP(6,"VIP"),
	VIP_ipa(7,"VIP苹果支付"),
	weeks_card(8,"周卡"),
	dial_experience(9,"畅聊体验"),
	
	
	
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(RechargeCategoryPriceEnum refer : RechargeCategoryPriceEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private RechargeCategoryPriceEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
	}
	
	public static RechargeCategoryPriceEnum getByCode(int code) {
		for (RechargeCategoryPriceEnum refer : RechargeCategoryPriceEnum.values())
			if (code == refer.getCode())
				return refer;
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

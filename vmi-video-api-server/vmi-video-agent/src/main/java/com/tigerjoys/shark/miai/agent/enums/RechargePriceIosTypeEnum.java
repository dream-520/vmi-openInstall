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
public enum RechargePriceIosTypeEnum {

	video(0,"视频"),  //H5
	audio(1, "音频"), //H5
	ios_com_tjhj_miyou(2, "IPA蜜友"),
	ios_com_duidui_duijiaoyou(3,"IPA对对"),
	ios_com_xqjy_milian(4,"IPA蜜恋"),
	ios_com_xq_yuanyuan(5,"IPA缘缘"),
	ios_com_jiaoyou_quliao(6,"IPA趣聊"),
	
	;
	
	private int code;
	private String desc;
	
	private static final Map<Integer , String> err_desc = new HashMap<Integer , String>();
	
	static {
		for(RechargePriceIosTypeEnum refer : RechargePriceIosTypeEnum.values()) {
			err_desc.put(refer.getCode(), refer.getDesc());
		}
	}
	
	private RechargePriceIosTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(int code) {
		return err_desc.get(code);
	}
	
	public static RechargePriceIosTypeEnum getByCode(int code) {
		for (RechargePriceIosTypeEnum refer : RechargePriceIosTypeEnum.values())
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

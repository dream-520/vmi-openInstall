package org.shark.miai.common.enums;

/**
 * 平台类型
 * @author chengang
 *
 */
public enum PlatformEnum {
	
	unknown(0,"未知"),
	android(1,"安卓"),
	ios(2,"IOS"),
	H5(3, "HTML5"),
 	;
	
	/**
	 * 类型
	 */
	public final int type;
	/**
	 * 类型描述
	 */
	public final String desc;
	
	private PlatformEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

}

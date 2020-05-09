package org.shark.miai.common.enums;

import java.util.HashMap;
import java.util.Map;


public enum AppNameEnum {
	unknow("",PlatformEnum.android,"0","V密"), 
	ios_app_yoyo_a1("app.yoyo.a1",PlatformEnum.ios,"0","V密"), 
	ios_com_yoyo_yushen("com.yoyo.yushen",PlatformEnum.ios,"6","蜜聊"), 
	ios_com_yo_miliao("com.yo.miliao",PlatformEnum.ios,"7","密约"), 
	ios_com_yo_miliaoliao("com.yo.miliaoliao",PlatformEnum.ios,"8","V密"), 
	ios_com_hailiao_hailiao("com.hailiao.hailiao",PlatformEnum.ios,"9","觅聊"), 
	ios_com_ailiao_ailiao("com.ailiao.ailiao",PlatformEnum.ios,"10","爱聊"), 
	ios_com_yjlt_yjlt("com.yjlt.yjlt",PlatformEnum.ios,"11","遇见聊天"), 
	ios_com_lehai_lehai("com.lehai.lehai",PlatformEnum.ios,"12","爱聊"), 
	ios_com_vmiliao_vmiliao("com.vmiliao.vmiliao",PlatformEnum.ios,"13","V密聊"), 
	ios_com_paopao_paopao("com.paopao.paopao",PlatformEnum.ios,"14","泡泡"), 
	ios_com_yujian2019_yujian2019("com.yujian2019.yujian2019",PlatformEnum.ios,"15","有聊吧"), 
	ios_com_qiguo_qiguo("com.qiguo.qiguo",PlatformEnum.ios,"16","奇果"), 
	ios_com_miqu_miqu("com.miqu.miqu",PlatformEnum.ios,"17","蜜趣交友"), 
	ios_com_yuanlai_yuanlai("com.yuanlai.yuanlai",PlatformEnum.ios,"18","缘来"), 
	ios_com_miguo_miguo("com.miguo.miguo",PlatformEnum.ios,"19","蜜果交友"), 
	ios_com_yljy_yljy("com.yljy.yljy",PlatformEnum.ios,"19","V Talk"), 
	ios_com_vvtalk_vvtalk("com.vvtalk.vvtalk",PlatformEnum.ios,"19","V Talk"), 
	ios_com_zdkj_lttool("com.zdkj.lttool",PlatformEnum.ios,"20","大V助手"), 
	ios_com_tjhj_miyou("com.tjhj.miyou",PlatformEnum.ios,"20","蜜友"), 
	ios_com_duidui_duijiaoyou("com.duidui.duijiaoyou",PlatformEnum.ios,"20","对对"), 
	ios_com_xqjy_milian("com.xqjy.milian",PlatformEnum.ios,"20","蜜恋相亲交友"), 
	ios_com_xq_yuanyuan("com.xq.yuanyuan",PlatformEnum.ios,"20","缘缘相亲"), 
	ios_com_jiaoyou_quliao("com.jiaoyou.quliao",PlatformEnum.ios,"20","趣聊交友"), 
	andriod_com_ydwx_yoyo("com.ydwx.yoyo",PlatformEnum.android,"0","V密"), 
	andriod_com_tjhj_miliao("com.tjhj.miliao",PlatformEnum.android,"1","蜜聊"), 
	andriod_com_ydwx_yoyo3("com.ydwx.yoyo3",PlatformEnum.android,"2","密约"), 
	andriod_com_tjhj_dvzs("com.tjhj.dvzs",PlatformEnum.android,"20","大V助手"), 
	andriod_com_ydwx_yoyo2("com.ydwx.yoyo2",PlatformEnum.android,"20","陌约"), 
	andriod_com_vmi_noshop("com.vmi.noshop",PlatformEnum.android,"20","V密"), 
	andriod_com_yoyo_jiaoyouliao("com.yoyo.jiaoyouliao",PlatformEnum.android,"20","YoYo"), 
	andriod_com_miliao_miliaoliao("com.miliao.miliaoliao",PlatformEnum.android,"20","蜜聊"), 
 	;
	
	/**
	 * 包名
	 */
	private String packageName;
	
	/**
	 * 消息类型  1 andriod  2 苹果  
	 */
	private  PlatformEnum osType;
	/**
	 * 渠道名
	 */
	private String channel;
	/**
	 * APP名称
	 */
	private String desc;
	
	private static final Map<String , AppNameEnum> STATUS_DESC = new HashMap<String , AppNameEnum>();
	
	static {
		for(AppNameEnum refer : AppNameEnum.values()) {
			STATUS_DESC.put(refer.getPackageName(), refer);
		}
	}
	
	private AppNameEnum(String packageName,PlatformEnum osType,String channel, String desc) {
		this.packageName = packageName;
		this.osType = osType;
		this.channel = channel;
		this.desc = desc;
	}
	
	public static String getByDesc(String packageName) {
		AppNameEnum value = STATUS_DESC.get(packageName);
		if(value == null){
			value =  unknow;
		}
		return value.getDesc();
	}
	
	public static String getByChannel(String packageName) {
		AppNameEnum value = STATUS_DESC.get(packageName);
		if(value == null){
			value =  unknow;
		}
		return value.getChannel();
	}

	public static PlatformEnum getByOsType(String packageName) {
		AppNameEnum value = STATUS_DESC.get(packageName);
		if(value == null){
			value =  unknow;
		}
		return value.getOsType();
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PlatformEnum getOsType() {
		return osType;
	}

	public void setOsType(PlatformEnum osType) {
		this.osType = osType;
	}
	
	
}

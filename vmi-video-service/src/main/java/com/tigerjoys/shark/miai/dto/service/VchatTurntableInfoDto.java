package com.tigerjoys.shark.miai.dto.service;

/**
 * 转盘初始数据
 * 参数 otherId  对方ID
 * 
 */
public class VchatTurntableInfoDto {
	/**
	 * 转盘图片地址
	 */
	private String  turntablePic;
	/**
	 * 总转盘格数 从1开始  如果为 0 ,表示主播端
	 */
	private Integer  total;
	/**
	 * 规则说明
	 */
	private String  ruleText;
	
	
	
	public String getTurntablePic() {
		return turntablePic;
	}
	public void setTurntablePic(String turntablePic) {
		this.turntablePic = turntablePic;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getRuleText() {
		return ruleText;
	}
	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
	}
	
	
	
}

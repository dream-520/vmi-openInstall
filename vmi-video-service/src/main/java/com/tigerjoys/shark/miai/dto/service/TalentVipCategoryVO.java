package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 达人VIP 分类 VO 对象
 * @author chengang
 *
 */
public class TalentVipCategoryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6648646262206493676L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * ICON地址
	 */
	private String icon;
	
	/**
	 * 延长月数
	 */
	private int months;
	
	/**
	 * 钻石
	 */
	private int diamond;
	
	/**
	 * 是否选中,1选中,0未选择
	 */
	private int checked;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getDiamond() {
		return diamond;
	}

	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

}

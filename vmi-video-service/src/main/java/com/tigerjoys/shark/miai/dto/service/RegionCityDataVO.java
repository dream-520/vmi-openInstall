package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;
import java.util.List;

/**
 * 生成客户端城市地区VO对象
 * @author chengang
 *
 */
public class RegionCityDataVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8036848331000245071L;
	
	/**
	 * 城市ID,[暂定为baiduCode]
	 */
	private int area;
	
	/**
	 * 城市名称
	 */
    private String areaName;
    
    /**
     * 首字母
     */
    private String initial;
    
    /**
     * 全拼
     */
    private String spell;
    
    /**
     * 所属市区
     */
    private List<RegionCityDataVO> childrenList;

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public List<RegionCityDataVO> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<RegionCityDataVO> childrenList) {
		this.childrenList = childrenList;
	}

}

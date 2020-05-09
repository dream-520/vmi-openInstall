package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 达人等级DTO
 * @author chengang
 *
 */
public class TalentLevelDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2924539760355414209L;
	
	/**
	 * 等级
	 */
	private int level;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 图片地址,HttP地址
	 */
	private String pic;
	
	/**
	 * 接单数
	 */
	private int accept;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getAccept() {
		return accept;
	}

	public void setAccept(int accept) {
		this.accept = accept;
	}

}

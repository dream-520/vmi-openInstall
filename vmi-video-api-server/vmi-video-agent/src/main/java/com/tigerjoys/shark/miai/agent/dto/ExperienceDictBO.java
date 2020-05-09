package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 经验值字典业务DTO
 * @author chengang
 *
 */
public class ExperienceDictBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1838112124666223286L;
	
	/**
	 * 字典ID
	 */
	private long dictid;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 添加的经验值
	 */
	private long exp;
	
	/**
	 * 增加类型,0仅一次,1每日,2累计,3其他
	 */
	private int type;
	
	/**
	 * 次数限制,仅每日和累计生效,0不限次数
	 */
	private int num;
	
	/**
	 * 是否乘以系统,0否,1是
	 */
	private int ratio;
	
	/**
	 * 优先级
	 */
	private int priority;
	
	/**
	 * 状态,0停用,1正常
	 */
	private int status;

	public long getDictid() {
		return dictid;
	}

	public void setDictid(long dictid) {
		this.dictid = dictid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

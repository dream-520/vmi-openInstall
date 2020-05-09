package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 用户的父级
 * @author yangjunming
 *
 */
public class UserInviteOfParentDto implements Serializable {
	
	/**
	 * 用户ID
	 */
	private Long userid;
	
	/**
	 * 是否有上级
	 */
	private boolean parentFlag;
	
	/**
	 * 上级包名
	 */
	private String packageName;
	
	/**
	 * 上3级用户
	 */
	private long parent1;
	/**
	 * 上2级用户
	 */
	private long parent2;
	/**
	 * 上1级用户
	 */
	private long parent3;
	

	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public long getParent1() {
		return parent1;
	}
	public void setParent1(long parent1) {
		this.parent1 = parent1;
	}
	public long getParent2() {
		return parent2;
	}
	public void setParent2(long parent2) {
		this.parent2 = parent2;
	}
	public long getParent3() {
		return parent3;
	}
	public void setParent3(long parent3) {
		this.parent3 = parent3;
	}
	public boolean isParentFlag() {
		return parentFlag;
	}
	public void setParentFlag(boolean parentFlag) {
		this.parentFlag = parentFlag;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
}

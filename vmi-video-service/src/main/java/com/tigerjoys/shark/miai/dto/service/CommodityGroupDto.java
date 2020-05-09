package com.tigerjoys.shark.miai.dto.service;


/**
 * 用户领取奖品分组关系dto
 * @author lipeng
 *
 */
/**
 * @author lipeng
 *
 */
public class CommodityGroupDto{
	
	
	/**
	 * Id
	 */
	private long id;
	
	/**
	 * 分组id
	 */
	private long group_id;
	
	/**
	 * 分组标题
	 */
	private String group_detail;
	
	/**
	 * 创建时间
	 */
	private String creat_time;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public String getGroup_detail() {
		return group_detail;
	}

	public void setGroup_detail(String group_detail) {
		this.group_detail = group_detail;
	}

	public String getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}
	
}

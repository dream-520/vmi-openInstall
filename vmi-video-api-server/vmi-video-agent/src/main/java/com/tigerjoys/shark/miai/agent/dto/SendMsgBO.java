package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 道具BO
 * @author lipeng
 *
 */
public class SendMsgBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8123531569093625922L;

	/**
	 * ID
	 */
	private long id;
	
	/**
	 * 道具名称
	 */
	private String name;

	/**
	 * 道具属性ID
	 */
	private Integer prop_type_id;
	
	/**
	 * 道具属性名称
	 */
	private String prop_type_name;
	
	/**
	 * 使用范围
	 */
	private String use_limit;
	
	/**
	 * 使用范围名称
	 */
	private String use_limit_name;
	
	/**
	 * 持续时间1:单次 0其他
	 */
	private Integer duration_time;
	
	/**
	 * 单次操作位置索引
	 */
	private Long location_index;
	
	/**
	 * 持续时间名称
	 */
	private String duration_time_name;
	
	/**
	 * 功能作用
	 */
	private String functional_action;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 道具图标
	 */
	private String icon;
	
	/**
	 * 道具介绍
	 */
	private List<String> introduce; 
	
	/**
	 * 状态,0下架,1上架,-9删除
	 */
	private Integer status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUse_limit() {
		return use_limit;
	}

	public void setUse_limit(String use_limit) {
		this.use_limit = use_limit;
	}

	public Integer getDuration_time() {
		return duration_time;
	}

	public void setDuration_time(Integer duration_time) {
		this.duration_time = duration_time;
	}

	public String getFunctional_action() {
		return functional_action;
	}

	public void setFunctional_action(String functional_action) {
		this.functional_action = functional_action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProp_type_id() {
		return prop_type_id;
	}

	public void setProp_type_id(Integer prop_type_id) {
		this.prop_type_id = prop_type_id;
	}

	public String getProp_type_name() {
		return prop_type_name;
	}

	public void setProp_type_name(String prop_type_name) {
		this.prop_type_name = prop_type_name;
	}

	public List<String> getIntroduce() {
		return introduce;
	}

	public void setIntroduce(List<String> introduce) {
		this.introduce = introduce;
	}

	public String getUse_limit_name() {
		return use_limit_name;
	}

	public void setUse_limit_name(String use_limit_name) {
		this.use_limit_name = use_limit_name;
	}

	public String getDuration_time_name() {
		return duration_time_name;
	}

	public void setDuration_time_name(String duration_time_name) {
		this.duration_time_name = duration_time_name;
	}

	public Long getLocation_index() {
		return location_index;
	}

	public void setLocation_index(Long location_index) {
		this.location_index = location_index;
	}

}

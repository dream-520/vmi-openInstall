package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 用户勿扰数据实体
 * @author shiming
 *
 */
public class UserDisturbDto {

    /**
     * 是否满足条件 0 不满足1 满足
     */
    private int condition;
    /**
     * 不满足条件提示文字
     */
    private String content;

    /**
     * 全局勿扰设置是否开启 0 未开启 1 开启
     */
    private int status;
    
    /**
     * 特殊描述信息
     */
    private String desc;
    
    /**
     * 单一设置数据
     */
    private List<DisturbItem> data;

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDes(String desc) {
		this.desc = desc;
	}

	public List<DisturbItem> getData() {
		return data;
	}

	public void setData(List<DisturbItem> data) {
		this.data = data;
	}
    
}

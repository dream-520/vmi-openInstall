package com.tigerjoys.shark.miai.agent.dto.transfer;

import java.io.Serializable;

/**
 * 道具功能参数DTO
 * @author lipeng
 *
 */
public class PropFunctionDto implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7916562965529445248L;

	/**
	 * 道具属性
	 */
	private Integer type;
	
	/**
	 * 运算符号
	 */
	private String opt;
	
	/**
	 * 运算值
	 */
	private Double value;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}

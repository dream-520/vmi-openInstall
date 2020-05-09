package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 用户信用记录DTO
 * @author 刘满
 *
 */
public class CreditRecordBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3304839406652140446L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 加减的分数值描述
	 */
	private String scoreDesc;
	
	/**
	 * 信用分加减来源
	 */
	private String source;
	
	/**
	 * 创建时间
	 */
	private String createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getScoreDesc() {
		return scoreDesc;
	}

	public void setScoreDesc(String scoreDesc) {
		this.scoreDesc = scoreDesc;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
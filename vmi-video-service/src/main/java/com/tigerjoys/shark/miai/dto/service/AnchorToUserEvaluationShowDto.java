package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 用户对主播评价DTO
 * @author yangjunming
 *
 */
public class AnchorToUserEvaluationShowDto {
	
    /**
     * 收益文字
     */
    private String income;

    /**
     * 被评价的用户
     */
    private long otherUserId;
    
    
    /**
     * 订单号
     */
    private long serialNum;

    /**
     * 评价选项
     */
    private List<AnchorToUserEvaluationDto> optionList;
    
    /**
     * 提示文字
     */
    private String tips;

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public long getOtherUserId() {
		return otherUserId;
	}

	public void setOtherUserId(long otherUserId) {
		this.otherUserId = otherUserId;
	}

	public List<AnchorToUserEvaluationDto> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<AnchorToUserEvaluationDto> optionList) {
		this.optionList = optionList;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public long getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(long serialNum) {
		this.serialNum = serialNum;
	}

	
}

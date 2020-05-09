package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 用户对主播评价DTO
 * @author yangjunming
 *
 */
public class AnchorEvaluationDto {
	
	/**
	 * 显示信息
	 */
	private String showInfo;
	
	/**
	 * 可选择数量
	 */
	private int selectNum;
	
	/**
	 * 评价列表
	 */
	private List<EvaluationDto> evaluationList;

	public String getShowInfo() {
		return showInfo;
	}

	public void setShowInfo(String showInfo) {
		this.showInfo = showInfo;
	}

	public int getSelectNum() {
		return selectNum;
	}

	public void setSelectNum(int selectNum) {
		this.selectNum = selectNum;
	}

	public List<EvaluationDto> getEvaluationList() {
		return evaluationList;
	}

	public void setEvaluationList(List<EvaluationDto> evaluationList) {
		this.evaluationList = evaluationList;
	}
	
	
}

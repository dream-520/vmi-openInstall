package com.tigerjoys.shark.miai.agent.dto;

import java.util.List;

public class TextAutioMsgDto {
	
	private long orderId;
	
	private  String taskId;
	
	private int action;
	
	private List<TextAudioLabelDto> labels;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public List<TextAudioLabelDto> getLabels() {
		return labels;
	}

	public void setLabels(List<TextAudioLabelDto> labels) {
		this.labels = labels;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	

}

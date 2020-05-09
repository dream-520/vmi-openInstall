package com.tigerjoys.shark.miai.agent.dto;

import java.util.List;

/**
 * 语音回调消息labels
 * @author yangjunming
 *
 */
public class TextAudioLabelDto {
	private  int label;
	private  int level;
	private  List<String> hintInfo;
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<String> getHintInfo() {
		return hintInfo;
	}
	public void setHintInfo(List<String> hintInfo) {
		this.hintInfo = hintInfo;
	}
	
}

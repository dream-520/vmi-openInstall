package com.tigerjoys.shark.miai.dto.service;

public class AnchorHelperDto extends UserBaseInfo {
	/**
	 * 0 都不显示  1 音频  2视频
	 */
	private int btnAction;

	public int getBtnAction() {
		return btnAction;
	}

	public void setBtnAction(int btnAction) {
		this.btnAction = btnAction;
	}

}

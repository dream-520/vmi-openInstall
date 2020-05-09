package com.tigerjoys.shark.miai.dto.service;

public class WarnInfo {

	/**
	 * 已经发送警告的次数
	 */
	private int num;
	
	/**
	 * 最近一次发送警告的时间戳
	 */
	private long sec;
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public long getSec() {
		return sec;
	}
	
	public void setSec(long sec) {
		this.sec = sec;
	}
	
}

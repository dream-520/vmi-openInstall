package com.tigerjoys.shark.miai.dto.service;

/**
 * 一键预约首页Dto实体
 * @author shiming
 *
 */
public class WebSubscribeUserDto {

	private long id;
	
	private String count;
	
	private int state;
	
	private String surplus;
	
	private String time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}

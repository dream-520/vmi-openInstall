package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 刷新普通约会信息dto
 * @author liuman
 *
 */
public class RefreshFreeInfoDto implements Serializable {

	private static final long serialVersionUID = -7517637503653106629L;
	
	/**
	 * 约会id
	 */
	private long appointId;
	/**
	 * 刷新条目的位置索引
	 */
	private int position;
	/**
	 * 约会状态:0-已关闭,1-正常
	 */
	private int appointStatus;
	
	/**
	 * 约会刷新状态:0-当天未刷新,1-当天已经刷新
	 */
	private int refreshStatus;

	public long getAppointId() {
		return appointId;
	}

	public void setAppointId(long appointId) {
		this.appointId = appointId;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getAppointStatus() {
		return appointStatus;
	}

	public void setAppointStatus(int appointStatus) {
		this.appointStatus = appointStatus;
	}

	public int getRefreshStatus() {
		return refreshStatus;
	}

	public void setRefreshStatus(int refreshStatus) {
		this.refreshStatus = refreshStatus;
	}

}

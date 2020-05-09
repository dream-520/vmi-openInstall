package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 发送短信好的返回结果
 * @author lvshouyang
 *
 */
public class SendSmsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7883352649362242299L;
	
	/**
	 * 成功数量
	 */
	private int succCount;
	
	/**
	 * 失败数量
	 */
	private int failCount;

	public int getSuccCount() {
		return succCount;
	}

	public void setSuccCount(int succCount) {
		this.succCount = succCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

}

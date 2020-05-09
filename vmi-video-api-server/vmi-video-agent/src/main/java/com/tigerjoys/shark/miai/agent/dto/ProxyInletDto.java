package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 代理人是否线上，线下
 * @author yangjunming
 *
 */
public class ProxyInletDto implements Serializable {
	/**
	 * 用户ID
	 */
	private Long userid;
	/**
	 * 是否是代理人
	 */
	private boolean isProxy;
	/**
	 * 代理入口  0 线下 1线上   参考 ProxyInletEnum
	 */
	private int proxyInlet;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public boolean isProxy() {
		return isProxy;
	}
	public void setProxy(boolean isProxy) {
		this.isProxy = isProxy;
	}
	public int getProxyInlet() {
		return proxyInlet;
	}
	public void setProxyInlet(int proxyInlet) {
		this.proxyInlet = proxyInlet;
	}
	
}

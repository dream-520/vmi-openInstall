package com.tigerjoys.shark.miai.agent;

/**
 * 根据提供的设备id查询是否满足对应的价格服务
 * @author shiming
 *
 */
public interface IDevicePriceAgent {

	public boolean mobileModelMoreThanPrice(String clientId, int price) throws Exception;
	
}

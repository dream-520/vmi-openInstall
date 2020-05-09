package com.tigerjoys.shark.miai.agent;

public interface IAnchorDynamicPriceAgent {

	/**
	 * 获取对应的用户设置的价格比例
	 */
	public int getUserScale(long userid);
	
	/**
	 * 根据用户来获取对应的主播价格
	 * @param userid
	 * @param price
	 * @return
	 */
	public int getAnchorDynamicPrice(long userid, int price);
}

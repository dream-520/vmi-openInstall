package com.tigerjoys.shark.miai.service;

/**
 * 统一效验头部渠道信息的是否是某种特殊渠道的服务
 * @author shiming
 */
public interface IChannelCheckService {

	/**
	 * 受提审状态和小黑屋的双重影响
	 * @return
	 */
	public boolean checkChannel();
	
	/**
	 * 处理个人主页的举报按钮  只在提审状态下起作用  不受小黑屋用户的影响
	 */
	public boolean checkChannelReport();
	
	/**
	 * 检测是否仅显示假数据
	 */
	public boolean checkShowFakeData();
	
	/**
	 * 控制发送假消息
	 */
	public boolean checkSendMessage();
	
	/**
	 * 控制显示假来电
	 */
	public boolean checkShowDail();
	
	/**
	 * 注册用户送小红花
	 */
	public boolean checkSendFlower();
	
}

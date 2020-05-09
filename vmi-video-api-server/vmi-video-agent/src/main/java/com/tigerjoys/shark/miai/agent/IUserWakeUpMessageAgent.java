package com.tigerjoys.shark.miai.agent;

/**
 * 用户在自动登录过程中触发的通知栏消息
 * @author shiming
 *
 */
public interface IUserWakeUpMessageAgent {

	public void wakeUpMessage(long userid) ;
}

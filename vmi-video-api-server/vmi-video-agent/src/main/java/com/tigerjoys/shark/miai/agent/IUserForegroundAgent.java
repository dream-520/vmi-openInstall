package com.tigerjoys.shark.miai.agent;

/**
 * 用户是否处于前台状态
 * @author shiming
 *
 */
public interface IUserForegroundAgent {

	public boolean existsForegroundUser(long userid) throws Exception;
	
	public void addForegroundUser(long userid) throws Exception;
	
	public void removeForegroundUser(long userid) throws Exception;
	
	public boolean existsForegroundAnchor(long userid) throws Exception;
	
	public void addForegroundAnchor(long userid) throws Exception;
	
	public void removeForegroundAnchor(long userid) throws Exception;
	
	public void addBackgroundAnchor(long userid) throws Exception;
	
	public void removeBackgroundAnchor(long userid) throws Exception;
	
	public int getBackgroundAnchorScore(long userid) throws Exception;
}

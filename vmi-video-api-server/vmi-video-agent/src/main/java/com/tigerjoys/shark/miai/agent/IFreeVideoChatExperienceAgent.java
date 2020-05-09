package com.tigerjoys.shark.miai.agent;

public interface IFreeVideoChatExperienceAgent {
	
	/**
	 * 
	 * @param userid
	 * @param channel
	 * @param second
	 * @param buyType   0  免费  1 购买
	 * @return
	 * @throws Exception
	 */
	public int insertRecord(long userid, String channel, int second,int buyType) throws Exception ;
}

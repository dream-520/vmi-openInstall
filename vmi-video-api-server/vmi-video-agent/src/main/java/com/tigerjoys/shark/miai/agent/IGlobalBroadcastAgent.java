package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;

/**
 * 全局广播服务代理接口
 * @author lipeng
 *
 */
public interface IGlobalBroadcastAgent {
	
	/**
	 * 根据ID获得用户对象，此方法走缓存
	 * @param userid - 用户ID
	 * @return UserBO
	 * @throws Exception
	 */
	public int getRecordCount(long userid) throws Exception;

	/**
	 * 添加全局广播信息
	 * @param userid
	 * @param anchorid
	 * @param money
	 * @param type
	 * @param giftId
	 * @throws Exception
	 */
	public void insert(Long userid, long anchorid, long money, int type, long giftId)throws Exception;

	/**
	 * 记录广播播放次数
	 * @param userid
	 * @return
	 */
	public AgentResult recordGlobalBroadcast(long userid);

}

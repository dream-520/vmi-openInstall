package com.tigerjoys.shark.miai.agent.service;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;

/**
 * 全局广播服务接口
 * @author lipeng
 *
 */
public interface IGlobalBroadcastAgentService {
	
	

	/**
	 * 添加充值信息进全局广播
	 * @param userid
	 * @param anchorid
	 * @param money
	 * @param type GlobalBroadcastTypeEnum
	 * @param giftId
	 * @throws Exception
	 */
	public void insert(Long userid, long anchorid, long money, int type, long giftId)throws Exception;

	
	/**
	 * 记录用户播放次数
	 * @param userid
	 * @return 
	 */
	public AgentResult recordGlobalBroadcast(long userid);


	/**
	 * 获得已经播报的次数
	 * @param userid
	 * @return
	 */
	public int getRecordCount(long userid);


}

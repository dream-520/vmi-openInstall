package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;

/**
 * 付费代理人
 * @author yangjunming
 *
 */
public interface IVchatRoomSettlementAgent {

	/**
	 * 主播视频 ,音频结算
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	public IncomeResultDto<Long> vchatRoomSettlement(long roomId) throws Exception;
}

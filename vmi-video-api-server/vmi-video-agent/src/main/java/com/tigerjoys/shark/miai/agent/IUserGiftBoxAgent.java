package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.UserChatGiftBoxLogTypeEnum;

/**
 * 礼物盒账户
 * @author yangjunming
 *
 */
public interface IUserGiftBoxAgent {

	
	/**
	 * 用户收入或消费
	 * @param userId - 用户ID
	 * @param otherId - 对方ID
	 * @param giftId - 礼物ID
	 * @param amount - 数量
	 * @param payType - 充值类型
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return AgentResult<Long> - 本次操作成功或者失败和原因，用户的礼物数量
	 * @throws Exception
	 */
	public AgentResult changeGiftBoxAccount(long userId,Long otherId,long giftId, long amount , UserChatGiftBoxLogTypeEnum type, String transactionFlow, String memo) throws Exception;
}

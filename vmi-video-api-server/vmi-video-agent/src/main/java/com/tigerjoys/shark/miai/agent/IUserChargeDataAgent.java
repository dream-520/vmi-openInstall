package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.UserChargeDataLogTypeEnum;

/**
 * 用户充值数据代理接口
 * @author chengang
 *
 */
public interface IUserChargeDataAgent {
	
	/**
	 * 用户现金账户更新并记录日志
	 * @param userid - 用户ID
	 * @param amount - 增加的数量
	 * @param logType - UserChargeDataLogTypeEnum
	 * @param memo - 备注
	 * @return AgentResult data存储变更后的余额
	 * @throws Exception
	 */
	public AgentResult changeChargeAmount(long userid, long amount, UserChargeDataLogTypeEnum logType, String memo) throws Exception;
	
	/**
	 * 获得用户累计充值金额
	 * @param userId - 用户ID
	 * @return long
	 */
	public long getTotalChargeAmount(long userId);

}

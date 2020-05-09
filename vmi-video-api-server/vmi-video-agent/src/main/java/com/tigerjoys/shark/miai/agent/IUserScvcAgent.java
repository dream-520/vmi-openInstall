package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.ScvcAwardCategoryEnum;

/** 
  * @author mouzhanpeng at [2018年1月24日 下午6:38:38] 
  * @since JDK 1.8.0 
  */
public interface IUserScvcAgent {

	/**
	 * 用户充值或消费
	 * @param userId 			- 用户ID
	 * @param amount 			- 个数
	 * @param io 				- 充值或者消费，0-消费、1-收入
	 * @param type 				- 充值或者消费的类型，参见  {@link ScvcAwardCategoryEnum}
	 * @param transactionFlow 	- 流水标识
	 * @param memo 				- 备注
	 * @return AgentResult 		- 本次操作成功或者失败和原因，用户的钻石余额
	 * @throws Exception
	 */
	public AgentResult changeScvcAccount(long userId, int amount, int io, int type, String transactionFlow, String memo) throws Exception;
	
	/**
	 * 用户活跃奖励
	 * @param userId			- 用户ID
	 * @param category			- {@link ScvcAwardCategoryEnum}
	 * @param transactionFlow 	- 流水标识
	 * @param memo 				- 备注
	 * @return AgentResult
	 * @throws Exception
	 */
	public AgentResult gainScvcVia(long userId, ScvcAwardCategoryEnum category, String transactionFlow, String memo) throws Exception;
	
	/**
	 * 用户SCVC余额
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public long getScvcBalance(long userId) throws Exception;
	
	/**
	 * 用户SCVC总额
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public long getScvcDeposit(long userId) throws Exception;
}

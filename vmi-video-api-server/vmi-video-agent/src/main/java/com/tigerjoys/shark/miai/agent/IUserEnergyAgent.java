/**
 * 
 */
package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;

/** 
 * ClassName: IUserDiamondAgent <br/> 
 * date: 2017年5月10日 下午4:45:57 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public interface IUserEnergyAgent {

	/**
	 * 用户充值或消费
	 * @param userId - 用户ID
	 * @param amount - 数量
	 * @param money - 充值钱数（分），消费为 null
	 * @param type - 充值或者消费的类型，参见@see {@link com.tigerjoys.shark.miai.agent.enums.UserEnergyAccountLogTypeEnum}
	 * @param logType - 0消费，1充值
	 * @param payType - 充值类型,消费为 null
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return DiamondResultDto<Long> - 本次操作成功或者失败和原因，用户的余额
	 * @throws Exception
	 */
	public AgentResult changeEnergyAccount(long userId, long amount, Long money, int type, int logType, Integer payType, String transactionFlow, String memo)
			throws Exception;

	/**
	 * 用户钻石余额
	 * @param userId
	 * @return
	 */
	public long getEnergyBalance(long userId);

	/**
	 * 查看对方信息扣能量
	 * @param userId     -用户ID
	 * @param otherId    -对方ID
	 * @param diamond    -钻
	 * @param type       -类型，1-手机，2-视频
	 * @return
	 * @throws Exception
	 */
	public AgentResult checkUserRight(long userId, long otherId, int diamond, int type) throws Exception;
}

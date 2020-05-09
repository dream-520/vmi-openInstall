package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.RedFlowerAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;

/** 
  * @author mouzhanpeng at [2017年12月20日 下午4:04:21] 
  * @since JDK 1.8.0 
  */
public interface IRedFlowerAgent {

	/**
	 * 红花账户添加红花
	 * @param userId
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	
	public AgentResult increaseFlowerAccount(long userId, long amount,RedFlowerAccountLogTypeEnum type,String transactionFlow) throws Exception;
	
	/**
	 * 消费红花 不产生收益
	 * @param userId
	 * @param amount
	 * @param type   {@link RedFlowerAccountLogTypeEnum }
	 * @param transactionFlow  流水号
	 * @return
	 * @throws Exception
	 */
	public AgentResult decreaseAllFlowers(long userId, double amount,RedFlowerAccountLogTypeEnum type, String transactionFlow) throws Exception;
	
	
	/**
	 * 消费红花，产生收益
	 * @param logType  
	 * @param userId
	 * @param otherid
	 * @param amount
	 * @param type    小红花日志类型 {@link RedFlowerAccountLogTypeEnum }
	 * @param incomeType   收益日志类型 {@link UserIncomeAccountLogTypeEnum }
	 * @param transactionFlow  流水号
	 * @return
	 * @throws Exception
	 */
	public AgentResult consumerFlowerAndIncome(long userId, long otherid, double amount,RedFlowerAccountLogTypeEnum type, UserIncomeAccountLogTypeEnum incomeType, String transactionFlow,String memo) throws Exception;
	
	/**
	 * 每天赠送红花(每天最多一次)
	 * @param userId
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public AgentResult increaseDailyFlower(long userId, long amount) throws Exception;
	
	/**
	 * 用户拥有红花数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public AgentResult getTotalFlowers(long userId) throws Exception;
	
	
	/**
	 * 
	 * 小红花消费收益入账
	 * 根据主播表分成
	 * @param type		  {@link UserIncomeAccountLogTypeEnum}
	 * @param userId			 			  用户ID
	 * @param amount              钻石数量
	 * @param transactionFlow     流水号
	 * @param memo								描述
	 * @return
	 * @throws Exception
	 */
	public IncomeResultDto<Long> chatFlowerIncome(UserIncomeAccountLogTypeEnum type, long userId, double amount, String transactionFlow, String memo) throws Exception ; 
	
	
	
	/**
	 * 用户拥有红花数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long getRedFlowerBalance(long userId) throws Exception;
	
	/**
	 * 获取总的小红花数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long getRedFlowerDeposit(long userId) throws Exception;
	
	/**
	 * 用户充值或消费
	 * @param userId - 用户ID
	 * @param amount - 钻石数量
	 * @param money - 充值钱数（分），消费为 null
	 * @param type - 充值或者消费的类型，参见@see {@link UserDiamondAccountLogTypeEnum}
	 * @param logType - 0消费，1充值
	 * @param payType - 充值类型,消费为 null
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return DiamondResultDto<Long> - 本次操作成功或者失败和原因，用户的钻石余额
	 * @throws Exception
	 */
	public AgentResult changeRedFlowerAccount(long userId, long amount, Long money, int type, int logType, Integer payType, String transactionFlow, String memo) throws Exception;
	
	/**
	 * 更新守护和VIP增送的缓存数据
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long getGuardVipTotalRedFlower(long userId) ;
	
	/**
	 * 查询当天有效小红花数量
	 * @param userId
	 * @return
	 */
	public long getCurrentCacheRedFlowerBalance(long userId);
}

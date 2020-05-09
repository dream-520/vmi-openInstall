package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.TaskCategoryEnum;
import com.tigerjoys.shark.miai.inter.entity.TaskEntity;

/**
 * @author mouzhanpeng at [2017年11月27日 下午3:28:19]
 * @since JDK 1.8.0
 */
public interface ITaskAgent {

	/**
	 * 完成任务
	 * @param userId	    -用户ID
	 * @param category		-任务体系 @see {@link TaskCategoryEnum#CHAT} 和 {@link TaskCategoryEnum#TALENT} 除外
	 * @throws Exception
	 */
	public AgentResult finishTask(long userId, TaskCategoryEnum category) throws Exception;
	
	/**
	 * 完成聊天任务
	 * @param from	    -聊天发起者ID
	 * @param to		-聊天接受者ID
	 * @throws Exception
	 */
	public AgentResult finishChatTask(long from, long to) throws Exception;

	/**
	 * 完成达人约任务
	 * @param userId	    -用户ID
	 * @param appointMoney  -约会订单金额（分）
	 * @param orderId       -约会订单号
	 * @param vacuate       -分成配置（当次方法返回成功时，会修改vacuate）
	 * @throws Exception
	 */
	public AgentResult finishTalentTask(long userId, long appointMoney, long orderId, VacuateConfigDto vacuate) throws Exception;
	
	/**
	 * 判断当前进行的任务
	 * @param level      -达人等级
	 * @param category   -任务体系
	 * @return
	 * @throws Exception
	 */
	public TaskEntity detectTask(int level, TaskCategoryEnum category) throws Exception;
	
	/**
	 * 任务收益提现
	 * @param money
	 * @param userId
	 * @param nickname
	 * @param via
	 * @param type
	 * @param name
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public IncomeResultDto<Long> withdrawalMoney(int money, long userId, String nickname, String name, String account) throws Exception;
	
	/**
	 * 拒绝提现，收益回滚
	 * @param amount
	 * @param userId
	 * @param type
	 * @param transactionFlow
	 * @param memo
	 * @return
	 * @throws Exception
	 */
	public IncomeResultDto<Long> withdrawalRollback(long amount, long userId, String transactionFlow, String memo) throws Exception;
	
	/**
	 * 任务收益可单独提现余额
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long balanceWithdrawalAlone(long userId) throws Exception;

	/**
	 * 任务收益可单独提现总额
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long depositWithdrawalAlone(long userId) throws Exception;
}

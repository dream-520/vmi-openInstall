/**
 * 
 */
package com.tigerjoys.shark.miai.agent;

import java.util.Map;

import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;

/**
 * ClassName: IUserIncomeAgent <br/>
 * date: 2017年5月10日 下午4:45:57 <br/>
 * 用户受益
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public interface IUserIncomeAgent {

	/**
	 * 用户收益增减(仅用于简单的账户增减)
	 * 
	 * @param userId       			- 用户ID
	 * @param originAmount 			- 收益额,初始收益额，未做分成处理
	 * @param amount       			- 分成后的收益
	 * @param io           			- 0提取，1收入
	 * @param type         			- 收益类型：1现金(服务收益或现金提取)，2票(礼物)，3现金（小费）
	 * @param transactionFlow   	- 流水标识
	 * @param vacuate
	 * @param memo					- 描述
	 * @return IncomeResultDto<Long>- 本次操作成功或者失败和原因，用户的收益余额
	 * @throws Exception
	 */
	public IncomeResultDto<Long> changeIncomeAccount(long userId,long originAmount, long amount, int io, UserIncomeAccountLogTypeEnum type, String transactionFlow, VacuateConfigDto vacuate, String memo) throws Exception;

	/**
	 * 用户收益增减(仅用于简单的账户增减)
	 * 
	 * @param userId       			- 用户ID
	 * @param amount       			- 分成后的收益
	 * @param io           			- 0提取，1收入
	 * @param type         			- 收益类型：1现金(服务收益或现金提取)，2票(礼物)，3现金（小费）
	 * @param transactionFlow   	- 流水标识
	 * @param memo					- 描述
	 * @return IncomeResultDto<Long>- 本次操作成功或者失败和原因，用户的收益余额
	 * @throws Exception
	 */
	public IncomeResultDto<Long> changeIncomeAccount(long userId, long amount, int io, UserIncomeAccountLogTypeEnum type, String transactionFlow, String memo) throws Exception;
	
	
	/**
	 * 用户不可提现收益增加
	 * 
	 * @param userId       			- 用户ID
	 * @param amount       			- 分成后的收益
	 * @param transactionFlow   	- 流水标识
	 * @param memo					- 描述
	 * @return IncomeResultDto<Long>- 本次操作成功或者失败和原因，用户的收益余额
	 * @throws Exception
	 */
	public IncomeResultDto<Long> userNoWithdrawalIncomeAdd(long userId, long amount, UserIncomeAccountLogTypeEnum type, String transactionFlow) throws Exception;
	
	
	/**
	 * 用户收益分成
	 * 
	 * @param userId 			- 用户ID
	 * @param amount 			- 收益额,初始收益额（对外），未做分成处理
	 * @param internalAmount 	- 内部收益额,对内收益额，未做分成处理
	 * @param type   			- 收益类型：1现金(服务收益或现金提取)，2票(礼物)
	 * @param vacuate			- 分成比列
	 * @param transactionFlow  	- 流水标识
	 * @param memo 	 			- 描述
	 * @return IncomeResultDto<Long> - 本次操作成功或者失败和原因，用户的收益余额
	 * @throws Exception
	 */
	public IncomeResultDto<Long> departIncomeAccount(long userId, long amount, UserIncomeAccountLogTypeEnum type, VacuateConfigDto vacuate, String transactionFlow, String memo) throws Exception;
	
	
	/**
	 * 用户收益分成
	 * 
	 * @param userId 			- 用户ID
	 * @param amount 			- 收益额,初始收益额（对外），未做分成处理
	 * @param internalAmount 	- 内部收益额,对内收益额，未做分成处理
	 * @param type   			- 收益类型：1现金(服务收益或现金提取)，2票(礼物)
	 * @param vacuate			- 分成比列
	 * @param transactionFlow  	- 流水标识
	 * @param memo 	 			- 描述
	 * @return IncomeResultDto<Long> - 本次操作成功或者失败和原因，用户的收益余额
	 * @throws Exception
	 */
	public IncomeResultDto<Long> departIncomeAccount(long userId, long amount,long internalAmount, UserIncomeAccountLogTypeEnum type, VacuateConfigDto vacuate, String transactionFlow, String memo) throws Exception;
	
	/**
	 * 
	 * 聊天钻石收益入账
	 * 根据主播表分成
	 * @param type							  1-文字聊天，4-聊天礼物  ,5-转盘, 6-体验用户通话激励      ( 删除  2-音频聊天，3-视频聊天) 
	 * @param userId			 			  用户ID
	 * @param amount              钻石数量
	 * @param transactionFlow     流水号
	 * @param memo								描述
	 * @return
	 * @throws Exception
	 */
	public IncomeResultDto<Long> chatDiamondIncome(int type, long userId, long amount, String transactionFlow, String memo) throws Exception;

	/**
	 * 用户收益余额
	 * 
	 * @param userId  - 用户ID
	 * @param type    - 类型:1现金，2票
	 * @return
	 */
	public long getIncomeBalance(long userId, AccountType type);

	/**
	 * 用户收益总额
	 * 
	 * @param userId  - 用户ID
	 * @param type    - 类型:1现金，2票
	 * @return
	 */
	public long getIncomeDeposit(long userId, AccountType type);

	/**
	 * 提现申请
	 * 
	 * @param money     - 申请金额（分）
	 * @param userId    - 用户Id
	 * @param nickname  - 昵称
	 * @param via       - 提现方式 1.余额 2.支付宝
	 * @param type      - 类型:1现金，2票
	 * @param name      - 用户姓名，可为空
	 * @param account   - 支付宝账号，可为空
	 * @return
	 * @throws Exception
	 */
	public IncomeResultDto<Map<String, Long>> withdrawalMoney(int money, long userId, String nickname, int via, UserIncomeAccountLogTypeEnum type, String name, String account) throws Exception;

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
	public IncomeResultDto<Long> withdrawalRollback(long amount, long userId, UserIncomeAccountLogTypeEnum type, String transactionFlow, String memo) throws Exception;

	/**
	 * 主播结算，给邀请者主播分成
	 * @param amount
	 * @param userId
	 * @param transactionFlow
	 * @return
	 * @throws Exception
	 */
	public IncomeResultDto<Long> withdrawalAddInvitationIncomes(long amount, long userId , String transactionFlow) throws Exception;
	
	/**
	 * 获取邀请收益
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long getInvitationIncomes(long userId) throws Exception;
	
	/**
	 * 获取不可提现收益额度
	 * 
	 * @param userId  - 用户ID
	 * @param type    - 类型:1现金，2票
	 * @return
	 */
	public long getNoWithdrawalBounds(long userId, AccountType type);
}

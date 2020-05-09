package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.TariffeResultDto;
import com.tigerjoys.shark.miai.agent.enums.UserTariffeAccountLogTypeEnum;

/** 
 * date: 2019年12月10日 下午4:45:57 <br/> 
 * 
 * @author lipeng 
 * @version  
 * @since JDK 1.8.0 
 */
public interface IUserTariffeAgent {

	/**
	 * 用户获得或兑换话费
	 * @param userid - 用户ID
	 * @param tariffe - 话费数量
	 * @param vipGive - 获得或兑换话费的类型，参见@see {@link UserTariffeAccountLogTypeEnum}
	 * @param logType - 0消费，1充值
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return TariffeResultDto<Long> - 本次操作成功或者失败和原因，用户的话费余额
	 * @throws Exception
	 */
	public TariffeResultDto<Long> changeTariffeAccount(long userid, long tariffe, int vipGive, int logType, String transactionFlow, String memo) throws Exception;

	/**
	 * 用户话费余额
	 * @param userid
	 * @return
	 */
	public long getTariffeBalance(long userid);
	
	/**
	 * 用户获得总话费
	 * @param userid
	 * @return
	 */
	public long getTariffeDeposit(long userid);

}

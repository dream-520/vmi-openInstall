/**
 * 
 */
package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.PointResultDto;
import com.tigerjoys.shark.miai.agent.enums.UserPointAccountLogTypeEnum;

/** 
 * ClassName: IUserPointAgent <br/> 
 * date: 2017年5月10日 下午4:45:57 <br/> 
 * 
 * @author lipeng 
 * @version  
 * @since JDK 1.8.0 
 */
public interface IUserPointAgent {

	/**
	 * 用户获得积分
	 * @param userid - 用户ID
	 * @param point - 积分数量
	 * @param type - 获得或兑换积分的类型，参见@see {@link UserPointAccountLogTypeEnum}
	 * @param logType - 0消费，1充值
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return PointResultDto<Long> - 本次操作成功或者失败和原因，用户的积分余额
	 * @throws Exception
	 */
	public PointResultDto<Long> changePointAccount(long userid, int type, int logType, String transactionFlow, String memo) throws Exception;
	
	/**
	 * 用户获得或兑换积分
	 * @param userid - 用户ID
	 * @param point - 积分数量
	 * @param type - 获得或兑换积分的类型，参见@see {@link UserPointAccountLogTypeEnum}
	 * @param logType - 0消费，1充值
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return PointResultDto<Long> - 本次操作成功或者失败和原因，用户的积分余额
	 * @throws Exception
	 */
	public PointResultDto<Long> changePointAccount(long userid, long point, int type, int logType, String transactionFlow, String memo) throws Exception;

	/**
	 * 用户积分余额
	 * @param userId
	 * @return
	 */
	public long getPointBalance(long userId);
	
	/**
	 * 用户获得总积分
	 * @param userId
	 * @return
	 */
	public long getPointDeposit(long userId);

}

package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.inter.entity.UserDailyWeekcardEntity;

/**
 * 礼物盒账户
 * @author yangjunming
 *
 */
public interface IUserWeekCardAgent {

	
	/**
	 * 购买周卡
	 * @param userId - 用户ID
	 * @param cardid - 周卡ID
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return AgentResult<Long> - 本次操作成功或者失败和原因，用户的礼物数量
	 * @throws Exception
	 */
	public AgentResult changeWeekCardAccount(long userId,long cardId, String transactionFlow, String memo) throws Exception;

	/**
	 * 获得每日周卡ID
	 * @param userid
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public long getDailyWeekCard(long userid,int type) throws Exception;
	
	/**
	 * 更新当日周卡时长
	 * @param userid
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public long updateDailyWeekCardDuration(long userid,int type) throws Exception;
	
	/**
	 * 获取当日周卡对象
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public UserDailyWeekcardEntity getDailyWeekCardEntity(long userId,int type) throws Exception;
	
	/**
	 * 获得时长
	 * @param userid
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public long getWeekCardTotalDuration(long userid,int type) throws Exception;
	
	/**
	 * 清除缓存并更新到当前日期
	 * @param userId
	 * @param type
	 * @throws Exception
	 */
	public void clearCacheCurrentDailyWeekCard(long userId, int type) throws Exception;
	
}

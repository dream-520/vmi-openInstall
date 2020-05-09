package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.BadgeTypeEnum;
import com.tigerjoys.shark.miai.inter.entity.BadgePriceEntity;

/** 
  * @author mouzhanpeng at [2017年11月13日 下午7:42:00] 
  * @since JDK 1.8.0 
  */
public interface IUserHonestyBadgeAgent {

	/**
	 * 购买诚信徽章
	 * @param userId
	 * @param badgeId
	 * @throws Exception
	 */
	public AgentResult buyHonestyBadge(long userId, long badgeId) throws Exception;
	
	/**
	 * 清空用户诚信徽章
	 * @param userId
	 * @throws Exception
	 */
	public void clearHonestyBadge(long userId) throws Exception;
	
	/**
	 * 根据徽章类别查询徽章信息
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public BadgePriceEntity findByCode(BadgeTypeEnum type) throws Exception;
}

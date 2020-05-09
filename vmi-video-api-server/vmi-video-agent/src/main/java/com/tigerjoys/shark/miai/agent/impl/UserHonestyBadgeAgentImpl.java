package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserHonestyBadgeAgent;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.BadgeTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IBadgePriceContract;
import com.tigerjoys.shark.miai.inter.contract.IUserHonestyBadgeContract;
import com.tigerjoys.shark.miai.inter.contract.IUserHonestyBadgeLogContract;
import com.tigerjoys.shark.miai.inter.entity.BadgePriceEntity;
import com.tigerjoys.shark.miai.inter.entity.UserHonestyBadgeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserHonestyBadgeLogEntity;

/** 
  * @author mouzhanpeng at [2017年11月13日 下午8:11:42] 
  * @since JDK 1.8.0 
  */
@Service
public class UserHonestyBadgeAgentImpl implements IUserHonestyBadgeAgent {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBadgePriceContract badgePriceContract;
	
	@Autowired
	private IUserHonestyBadgeContract userHonestyBadgeContract;
	
	@Autowired
	private IUserHonestyBadgeLogContract userHonestyBadgeLogContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult buyHonestyBadge(long userId, long badgeId) throws Exception {
		LOGGER.info("buyHonestyBadge:[" + "userId:" + userId + ",badgeId:" + badgeId + "]");
		BadgePriceEntity price = badgePriceContract.findById(badgeId);
		if(null == price){
			return AgentResult.fail(AgentErrorCodeEnum.db_not_found);
		}
		String orderId = String.valueOf(IdGenerater.generateId());
		DiamondResultDto<Long> result = userDiamondAgent.changeDiamondAccount(userId, price.getDiamond(), null, UserDiamondAccountLogTypeEnum.pay_honesty_badge.getCode(), 0, null, orderId, "钻石购买诚信徽章[" + price.getName() + "]");
		if(0 != result.getCode()){
			return AgentResult.fail(result.getCode(), result.getMsg());
		}
		UserHonestyBadgeEntity badge = userHonestyBadgeContract.lockByUserId(userId);
		Date date = new Date(), expire = null;
		if(null == badge){
			badge = new UserHonestyBadgeEntity();
			badge.setCreate_time(date);
			badge.setUpdate_time(date);
			badge.setUser_id(userId);
			badge.setStatus(1);
			expire = switchWrapData(badge, BadgeTypeEnum.getByCode(price.getCode()), price.getDuration());
			userHonestyBadgeContract.insert(badge);
		}else{
			badge.setUpdate_time(date);
			badge.setStatus(1);
			expire = switchWrapData(badge, BadgeTypeEnum.getByCode(price.getCode()), price.getDuration());
			userHonestyBadgeContract.update(badge);
		}
		
		UserHonestyBadgeLogEntity log = new UserHonestyBadgeLogEntity();
		log.setCreate_time(date);
		log.setUser_id(userId);
		log.setBadge_id(price.getId());
		log.setDiamond(price.getDiamond());
		log.setDuration(price.getDuration());
		log.setTransaction_flow(orderId);
		userHonestyBadgeLogContract.insert(log);
		// 更新用户徽章状态
		userAgent.updateHonestyBadge(userId, BadgeTypeEnum.getByCode(price.getCode()), expire);
		return AgentResult.success(result.getData());
	}
	
	/**
	 * 判断购买哪一种徽章
	 * ----》下版本需求可能修改，待扩展《----
	 * @param badge
	 * @param type
	 * @param months
	 */
	private Date switchWrapData(UserHonestyBadgeEntity badge, BadgeTypeEnum type, int months){
		Date date = new Date(System.currentTimeMillis() + months * Tools.MONTH_MILLIS);
		switch(type){
		case LOW_PROMISE:
			badge.setLow_expire_time(date);
			break;
		case MID_PROMISE:
			badge.setMid_expire_time(date);
			break;
		case HIGH_PROMISE:
			badge.setHigh_expire_time(date);
			break;
		default:
			throw new IllegalArgumentException("can not find the BadgeTypeEnum[" + type +"]!");
		}
		return date;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void clearHonestyBadge(long userId) throws Exception {
		LOGGER.info("clear user[{}] honesty badge !",userId);
		Map<String, Object> data = new HashMap<>();
		data.put("update_time", new Date());
		data.put("low_expire_time", null);
		data.put("mid_expire_time", null);
		data.put("high_expire_time", null);
		data.put("status", 0);
		if(1 == userHonestyBadgeContract.updateById(data, userId)){
			// 更新用户徽章状态
			userAgent.updateHonestyBadge(userId, BadgeTypeEnum.NO_PROMISE, null);
		}
	}
	
	@Override
	public BadgePriceEntity findByCode(BadgeTypeEnum type) throws Exception {
		if(BadgeTypeEnum.NO_PROMISE == type){
			return null;
		}
		return badgePriceContract.findByProperty("code", type.getCode());
	}
}

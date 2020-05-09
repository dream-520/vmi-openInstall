package com.tigerjoys.shark.miai.service;

import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.PhotoCheckedLogTypeEnum;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoCheckedLogEntity;

/**
 * 作品购买服务接口
 * @author chengang
 *
 */
public interface IPhotoCheckedLogService {
	
	/**
	 * 支付钻石购买主播的私密作品 
	 * @param userId - 购买者ID
	 * @param anchor - 主播信息
	 * @param photoId - 作品ID
	 * @param diamond - 花费钻石数量
	 * @param type 作品类型
	 * @return DiamondResultDto<Long> 剩余钻石数量
	 * @throws Exception
	 */
	public DiamondResultDto<Long> pay(long userId, AnchorOnlineEntity anchor, long photoId, int diamond, PhotoCheckedLogTypeEnum type) throws Exception;
	
	/**
	 * 获取私密作品购买记录 
	 * @param userId - 购买者ID
	 * @param photoId - 作品ID
	 * @param type 作品类型
	 * @return UserPhotoCheckedLogEntity
	 * @throws Exception
	 */
	public UserPhotoCheckedLogEntity getUserPhotoCheckedLog(long userId, long photoId, PhotoCheckedLogTypeEnum type) throws Exception;

}

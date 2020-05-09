package com.tigerjoys.shark.miai.inter.contract;

import java.util.List;
import java.util.Map;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoCheckedLogEntity;

/**
 * 数据库中  用户付费查看用户信息记录[t_user_photo_checked_log]表 接口类
 * @author chengang
 * @Date 2020-02-24 16:47:47
 *
 */
public interface IUserPhotoCheckedLogContract extends BaseContract<UserPhotoCheckedLogEntity> {
	
	/**
	 * 根据用户ID加锁查询获得对象
	 * @param userId - 用户ID
	 * @param photoId - 作品ID
	 * @param type - 作品类型
	 * @return UserPhotoCheckedLogEntity
	 */
	public UserPhotoCheckedLogEntity lockByUserId(long userId, long photoId, int type);
	
	/**
	 * 批量获取作品信息购买关系
	 * @param userId - 用户ID
	 * @param photoIdList - 作品ID列表
	 * @param type - 作品类型
	 * @return Map<Long, UserPhotoCheckedLogEntity>, key=作品ID
	 * @throws Exception
	 */
	public Map<Long, UserPhotoCheckedLogEntity> getUserPhotoCheckedLogs(long userId, List<Long> photoIdList, int type) throws Exception;
	
}

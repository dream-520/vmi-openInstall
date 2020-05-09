package com.tigerjoys.shark.miai.service;

import java.util.List;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.service.UserPhotoDto;
import com.tigerjoys.shark.miai.dto.service.UserVideoDto;

/**
 * 用户相册/视频接口
 * @author chengang
 *
 */
public interface IUserPhotoService {
	
	/**
	 * 用户相册列表
	 * @param userId - 用户ID
	 * @param anchorId - 主播ID
	 * @param stamp - 分页戳
	 * @return ActionResult
	 * @throws Exception
	 */
	public ActionResult userPhotoList(long userId, long anchorId, String stamp) throws Exception;
	
	/**
	 * 用户相册列表
	 * @param userId - 用户ID
	 * @param anchorId - 主播ID
	 * @param pageSize - 分页数量
	 * @param stamp - 分页戳
	 * @return List<UserPhotoDto>
	 * @throws Exception
	 */
	public List<UserPhotoDto> getUserPhotoList(long userId, long anchorId, int pageSize, String stamp) throws Exception;
	
	/**
	 * 用户视频列表
	 * @param userId - 用户ID
	 * @param anchorId - 直播ID
	 * @param stamp - 分页戳
	 * @return ActionResult
	 * @throws Exception
	 */
	public ActionResult userVideoList(long userId, long anchorId, String stamp) throws Exception;
	
	/**
	 * 用户视频列表
	 * @param userId - 用户ID
	 * @param anchorId - 主播ID
	 * @param pageSize - 分页数量
	 * @param stamp - 分页戳
	 * @return List<UserVideoDto>
	 * @throws Exception
	 */
	public List<UserVideoDto> getUserVideoList(long userId, long anchorId, int pageSize, String stamp) throws Exception;

}

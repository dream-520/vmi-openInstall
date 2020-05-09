package com.tigerjoys.shark.miai.es.service;

import java.util.Date;
import java.util.List;

import org.shark.miai.common.enums.UserTypeEnum;

import com.tigerjoys.shark.miai.es.dto.EsUserOnlineReordDto;

/**
 * 推送接口调用
 * @author liuman
 *
 */
public interface IEsUserOnlineRecordService {
	
	/**
	 * 保存在线用户
	 * @param dto
	 * @throws Exception
	 */
	public void saveEntity(EsUserOnlineReordDto dto) throws Exception;
	
	/**
	 * 查询时间范围内在线人数
	 * userType - userType  用户类型  @see{@link UserTypeEnum} ,null 为  所有类型用户
	 * @throws Exception
	 */
	public long queryUserOnlineNum(Date bigin, Date end ,UserTypeEnum userType) throws Exception;
	
	/**
	 * 查询时间范围内在线用户Id
	 * @param bigin 
	 * @param end
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<Long> queryUserOnline(Date bigin, Date end ,UserTypeEnum userType) throws Exception;
	
}

package com.tigerjoys.shark.miai.es.service;

import java.util.List;

import com.tigerjoys.shark.miai.es.dto.EsMobileUserAppListReordDto;

/**
 * 手机采集应用列表
 * @author yangjunming
 *
 */
public interface IEsMobileUserAppRecordService {
	
	/**
	 * 保存应用列表
	 * @param dto
	 * @throws Exception
	 */
	public void saveEntity(EsMobileUserAppListReordDto dto) throws Exception;
	
	/**
	 * 查最近用户装的 APP列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<String> queryLastLoginAppList(long userId) throws Exception;
	
}

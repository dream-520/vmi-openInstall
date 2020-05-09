package com.tigerjoys.shark.miai.es.service;

import java.util.List;

import com.tigerjoys.shark.miai.es.dto.EsMobileAppListReordDto;

/**
 * 手机采集应用列表
 * @author yangjunming
 *
 */
public interface IEsMobileAppListRecordService {
	
	/**
	 * 保存应用列表
	 * @param dto
	 * @throws Exception
	 */
	public void saveAll(List<EsMobileAppListReordDto> dto) throws Exception;
	
	/**
	 * 查询前size应用
	 * @param userType  用户类型   0  用户 1主播
	 * @param size  前多少个APP
	 * @throws Exception
	 */
	public List<String> queryTopApp(int userType,int size) throws Exception;
	
}

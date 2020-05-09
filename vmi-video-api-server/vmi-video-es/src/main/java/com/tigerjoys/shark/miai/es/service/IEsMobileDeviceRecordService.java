package com.tigerjoys.shark.miai.es.service;

import java.util.List;

import com.tigerjoys.shark.miai.es.dto.EsMobileDeviceReordDto;

/**
 * 手机采集应用列表
 * @author yangjunming
 *
 */
public interface IEsMobileDeviceRecordService {
	
	/**
	 * 保存应用列表
	 * @param dto
	 * @throws Exception
	 */
	public void saveAll(List<EsMobileDeviceReordDto> dto) throws Exception;
	
	/**
	 * 查询前100应用
	 * @throws Exception
	 */
	public List<String> queryTopApp(int size) throws Exception;
	
}

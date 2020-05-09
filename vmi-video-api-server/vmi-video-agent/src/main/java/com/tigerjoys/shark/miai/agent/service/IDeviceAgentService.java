package com.tigerjoys.shark.miai.agent.service;

import com.tigerjoys.shark.miai.agent.dto.DeviceBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceCreateDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceModifyDto;
import com.tigerjoys.shark.miai.inter.entity.DeviceEntity;

/**
 * 设备代理服务接口
 * @author chengang
 *
 */
public interface IDeviceAgentService {
	
	/**
	 * 根据ID设备实例
	 * @param deviceId - 设备ID
	 * @return DeviceBO
	 * @throws Exception
	 */
	public DeviceBO findById(long deviceId) throws Exception;
	
	/**
	 * 根据clientId设备实例
	 * @param clientId - 手机号码
	 * @return DeviceBO
	 * @throws Exception
	 */
	public DeviceBO findByClientId(String clientId) throws Exception;
	
	/**
	 * 根据Imei设备实例
	 * @param imei - String
	 * @return DeviceBO
	 * @throws Exception
	 */
	public DeviceBO findByImei(String imei) throws Exception;
	
	/**
	 * 创建设备信息
	 * @param dto - DeviceCreateDto
	 * @return DeviceBO
	 * @throws Exception
	 */
	public DeviceBO createDevice(DeviceCreateDto dto) throws Exception;
	
	/**
	 * 修改设备信息
	 * @param dto - DeviceModifyDto
	 * @throws Exception
	 */
	public void modifyDevice(DeviceModifyDto dto) throws Exception;
	
	/**
	 * 将设备的数据对象转换为DeviceBO对象
	 * @param device - DeviceEntity
	 * @return DeviceBO
	 * @throws Exception
	 */
	public DeviceBO transferDevice(DeviceEntity device) throws Exception;
	
	/**
	 * 关联设备和用户关系
	 * @param userid - 用户ID
	 * @param clientId - 设备ID
	 * @param appVersion - app版本
	 * @param versionCode - appCode
	 * @throws Exception
	 */
	public void relatedUserAndDevice(long userid , String clientId , String appVersion , int versionCode) throws Exception;

}

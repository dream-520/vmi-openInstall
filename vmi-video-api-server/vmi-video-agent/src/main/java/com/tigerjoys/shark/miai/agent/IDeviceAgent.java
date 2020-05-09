package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.agent.dto.DeviceBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceCreateDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceModifyDto;

/**
 * 设备代理接口
 * @author chengang
 *
 */
public interface IDeviceAgent {
	
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
	 * 创建新的设备信息
	 * @param dto - CreateDeviceDto
	 * @return PassDeviceDto
	 * @throws Exception
	 */
	public DeviceBO createDevice(DeviceCreateDto dto) throws Exception;
	
	/**
	 * 修改设备信息
	 * @param deviceId - 设备ID
	 * @param dto - ModifyDeviceDto
	 * @throws Exception
	 */
	public void modifyDevice(DeviceModifyDto dto) throws Exception;
	
	/**
	 * 关联用户和设备信息
	 * @param userid - 用户ID
	 * @param clientId - 设备ID
	 * @param appVersion - app版本
	 * @param versionCode - appCode
	 * @throws Exception
	 */
	public void relatedUserAndDevice(long userid , String clientId , String appVersion , int versionCode) throws Exception;

}

package com.tigerjoys.shark.miai.agent.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IDeviceAgent;
import com.tigerjoys.shark.miai.agent.dto.DeviceBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceCreateDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceModifyDto;
import com.tigerjoys.shark.miai.agent.service.IDeviceAgentService;

/**
 * 设备代理接口实现类
 * @author chengang
 *
 */
@Service
public class DeviceAgentImpl implements IDeviceAgent {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IDeviceAgentService deviceAgentService;

	@Override
	public DeviceBO findById(long deviceId) throws Exception {
		if(deviceId <= 0) return null;
		
		return deviceAgentService.findById(deviceId);
	}

	@Override
	public DeviceBO findByClientId(String clientId) throws Exception {
		if(Tools.isNull(clientId)) return null;
		
		return deviceAgentService.findByClientId(clientId);
	}

	@Override
	public DeviceBO findByImei(String imei) throws Exception {
		if(Tools.isNull(imei)) return null;
		
		return deviceAgentService.findByImei(imei);
	}

	@Override
	public DeviceBO createDevice(DeviceCreateDto dto) throws Exception {
		if(Tools.isNull(dto.getClientid())) throw new NullPointerException("clientid is null!");
		
		logger.info("createDevice : ",JsonHelper.toJson(dto));
		
		return deviceAgentService.createDevice(dto);
	}

	@Override
	public void modifyDevice(DeviceModifyDto dto) throws Exception {
		if(dto.getDeviceId() <= 0) throw new NullPointerException("deviceId is null");
		
		logger.info("modifyDevice : ",JsonHelper.toJson(dto));
		
		deviceAgentService.modifyDevice(dto);
	}

	@Override
	public void relatedUserAndDevice(long userid, String clientId , String appVersion , int versionCode) throws Exception {
		if(userid <= 0 || Tools.isNull(clientId)) throw new NullPointerException("userid lt 0 or deviceId is null");
		
		logger.info("relatedUserAndDevice , userid :" + userid + " , clientId : " + clientId + " , appVersion : " + appVersion+ " ,versionCode :" + versionCode);
		
		deviceAgentService.relatedUserAndDevice(userid, clientId , appVersion , versionCode);
	}

}

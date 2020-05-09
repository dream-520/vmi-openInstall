package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.BeanUtils;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.dto.DeviceBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceCreateDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceModifyDto;
import com.tigerjoys.shark.miai.agent.service.IDeviceAgentService;
import com.tigerjoys.shark.miai.inter.contract.IDeviceContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDeviceRefContract;
import com.tigerjoys.shark.miai.inter.entity.DeviceEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDeviceRefEntity;

/**
 * 设备代理服务接口实现类
 * @author chengang
 *
 */
@Service
public class DeviceAgentServiceImpl implements IDeviceAgentService {
	
	@Autowired
	private IDeviceContract deviceContract;
	
	@Autowired
	private IUserDeviceRefContract userDeviceRefContract;
	
	@Override
	public DeviceBO findById(long deviceId) throws Exception {
		return transferDevice(deviceContract.findById(deviceId));
	}

	@Override
	public DeviceBO findByClientId(String clientId) throws Exception {
		return transferDevice(deviceContract.findByProperty("clientid", clientId));
	}

	@Override
	public DeviceBO findByImei(String imei) throws Exception {
		return transferDevice(deviceContract.findByProperty("imei", imei));
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public DeviceBO createDevice(DeviceCreateDto dto) throws Exception {
		Date currDate = new Date();
		
		DeviceEntity device = new DeviceEntity();
		device.setAppversion(dto.getAppversion());
		device.setBssid(dto.getBssid());
		device.setChannel(dto.getChannel());
		device.setCountry_id(Tools.longValue(dto.getCountry_id()));
		device.setProvince_id(Tools.longValue(dto.getProvince_id()));
		device.setCity_id(Tools.longValue(dto.getCity_id()));
		device.setClientid(dto.getClientid());
		device.setCore(dto.getCore());
		device.setCore_model(dto.getCore_model());
		device.setCreate_time(currDate);
		device.setImei(dto.getImei());
		device.setImsi(dto.getImsi());
		device.setIp(dto.getIp());
		device.setLat(Tools.doubleValue(dto.getLat()));
		device.setLng(Tools.doubleValue(dto.getLng()));
		device.setMac(dto.getMac());
		device.setMobile(dto.getMobile());
		device.setMobile_brand(dto.getMobile_brand());
		device.setMobile_model(dto.getMobile_model());
		device.setOs_ver(dto.getOs_ver());
		device.setPkg_name(dto.getPkg_name());
		device.setPkgmd5(dto.getPkgmd5());
		device.setPlatform(Tools.intValue(dto.getPlatform()));
		device.setReg_appversion(dto.getAppversion());
		device.setReg_channel(dto.getChannel());
		device.setReg_city_id(Tools.longValue(dto.getCity_id()));
		device.setReg_versioncode(Tools.intValue(dto.getVersioncode()));
		device.setRom_remain(Tools.intValue(dto.getRom_remain()));
		device.setRom_volume(Tools.intValue(dto.getRom_volume()));
		device.setScreensize(dto.getScreensize());
		device.setSdk_remain(Tools.intValue(dto.getSdk_remain()));
		device.setSdk_ver(dto.getSdk_ver());
		device.setSdk_volume(Tools.intValue(dto.getSdk_volume()));
		device.setSsid(dto.getSsid());
		device.setStatus(1);
		device.setUpdate_time(currDate);
		device.setVersioncode(Tools.intValue(dto.getVersioncode()));
		device.setNotice_close(dto.getNoticeClose());
		
		//添加一个对应的androidid
		device.setAndroidId(dto.getAndroidId());
		
		deviceContract.insert(device);
		
		//创建用户跟设备的映射关系
		if(Tools.longValue(dto.getUserid()) > 0) {
			UserDeviceRefEntity ref = new UserDeviceRefEntity();
			ref.setCreate_time(currDate);
			ref.setDeviceid(device.getId());
			ref.setUserid(dto.getUserid());
			ref.setStatus(1);
			ref.setApp_version(dto.getAppversion());
			ref.setApp_version_code(dto.getVersioncode());
			userDeviceRefContract.insert(ref);
		}
		
		return transferDevice(device);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void modifyDevice(DeviceModifyDto dto) throws Exception {
		Date currDate = new Date();
		
		DeviceEntity temp = new DeviceEntity();
		temp.setId(dto.getDeviceId());
		temp.setMobile(dto.getMobile());
		temp.setCountry_id(dto.getCountry_id());
		temp.setProvince_id(dto.getProvince_id());
		temp.setCity_id(dto.getCity_id());
		temp.setLat(dto.getLat());
		temp.setLng(dto.getLng());
		temp.setBssid(dto.getBssid());
		temp.setSsid(dto.getSsid());
		temp.setCore(dto.getCore());
		temp.setCore_model(dto.getCore_model());
		temp.setAppversion(dto.getAppversion());
		temp.setVersioncode(dto.getVersioncode());
		temp.setChannel(dto.getChannel());
		temp.setIp(dto.getIp());
		temp.setUpdate_time(currDate);
		temp.setOs_ver(dto.getOs_ver());
		temp.setSdk_ver(dto.getSdk_ver());
		temp.setNotice_close(dto.getNoticeClose());
		temp.setAndroidId(dto.getAndroidId());
		deviceContract.update(temp);
		
		//创建用户跟设备的映射关系
		if(Tools.longValue(dto.getUserid()) > 0) {
			UserDeviceRefEntity ref = userDeviceRefContract.findUserDeviceRef(dto.getDeviceId(), dto.getUserid());
			if(ref == null) {
				ref = new UserDeviceRefEntity();
				ref.setCreate_time(currDate);
				ref.setDeviceid(dto.getDeviceId());
				ref.setUserid(dto.getUserid());
				ref.setStatus(1);
				ref.setApp_version(dto.getAppversion());
				ref.setApp_version_code(dto.getVersioncode());
				userDeviceRefContract.insert(ref);
			}
		}
	}

	@Override
	public DeviceBO transferDevice(DeviceEntity device) throws Exception {
		if(device == null) return null;
		
		DeviceBO dto = BeanUtils.copyBean(device, DeviceBO.class);
		dto.setDeviceId(device.getId());
		
		return dto;
	}

	@Override
	public void relatedUserAndDevice(long userid, String clientId , String appVersion , int versionCode) throws Exception {
		DeviceEntity device = deviceContract.findByProperty("clientid", clientId);
		if(device != null) {
			UserDeviceRefEntity ref = userDeviceRefContract.findUserDeviceRef(device.getId(), userid);
			if(ref == null) {
				ref = new UserDeviceRefEntity();
				ref.setCreate_time(new Date());
				ref.setDeviceid(device.getId());
				ref.setUserid(userid);
				ref.setStatus(1);
				ref.setApp_version(appVersion);
				ref.setApp_version_code(versionCode);
				userDeviceRefContract.insert(ref);
			}
		}
	}

}

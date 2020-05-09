package com.tigerjoys.shark.miai.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IDeviceAgent;
import com.tigerjoys.shark.miai.agent.IKuaiShouConfirmAgent;
import com.tigerjoys.shark.miai.agent.constant.LoggerAgentConst;
import com.tigerjoys.shark.miai.agent.dto.AreaDto;
import com.tigerjoys.shark.miai.agent.dto.DeviceBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceCreateDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceModifyDto;
import com.tigerjoys.shark.miai.agent.enums.KuaiShouAdTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.ThirdPartySpreadEnum;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.dto.service.AmassInfoDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IKuaiShouAdDataContract;
import com.tigerjoys.shark.miai.inter.contract.IThirdPartySpreadContract;
import com.tigerjoys.shark.miai.inter.entity.KuaiShouAdDataEntity;
import com.tigerjoys.shark.miai.inter.entity.ThirdPartySpreadEntity;
import com.tigerjoys.shark.miai.service.IInformationCollectService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * 信息手机服务实现类
 * @author chengang
 *
 */
@Service
public class InformationCollectServiceImpl implements IInformationCollectService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InformationCollectServiceImpl.class);
	
	private final Logger mobileInfoLogger = LoggerFactory.getLogger(LoggerAgentConst.AMASS_NEW_LOGGER);
	
	@Autowired
	private IDeviceAgent deviceAgent;
	
	@Autowired
	private IAppAreaService appAreaService;
	
	@Autowired
	private IKuaiShouConfirmAgent kuaiShouConfirmAgent;
	
	@Autowired
	private IKuaiShouAdDataContract kuaiShouAdDataContract;
	
	@Autowired
	private IThirdPartySpreadContract thirdPartySpreadContract;

	@Override
	public ActionResult amassInfo(AmassInfoDto info) throws Exception {
		if(info == null) {
			LOGGER.error("info is null");
			return ActionResult.fail();
		}

		RequestHeader header = RequestUtils.getCurrent().getHeader();
		String clientId = header.getClientId();
		
		if(Tools.isNull(clientId)) {
			LOGGER.warn("信息收集接口接收到的ClientId参数为Null，请检查...................................");
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode() , ErrorCodeEnum.parameter_error.getDesc());
		}
		info.setClientId(clientId);
		//LoggerConst.AMASS_LOGGER.info(info.toString());
		mobileInfoLogger.info(JsonHelper.toJson(info));
		
		try {
			if(Tools.isNull(info.getImei1())) {
				info.setImei1(null);
			}
			if(Tools.isNull(info.getImsi1())) {
				info.setImsi1(null);
			}
			//0在IOS中代表没有拿到数据
			if(Tools.intValue(info.getNoticeClose()) == 0) {
				info.setNoticeClose(null);
			}
			
			//先根据clientId获取设备信息
			DeviceBO device = deviceAgent.findByClientId(clientId);
			if(device != null) {
				updateDeviceInfo(info , device);
			}
			//根据IMEI获取设备信息
			if(device == null && Tools.isNotNull(info.getImei1())) {
				device = deviceAgent.findByImei(info.getImei1());
				if(device != null) {
					updateDeviceInfo(info , device);
				}
			}
			
			//如果device还是空，则创建新设备
			if(device == null) {
				device = createDeviceInfo(info);
				//检测是否属于对应的快手推广进行的注册用户
				if(Tools.isNotNull(header.getChannel()) && header.getChannel().equals("kuaishou_cpd")) {
					kuaiShouConfirmAgent.confirm(header.getClientId(), KuaiShouAdTypeEnum.device.getCode());
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		//处理三方广告平台快手的激活数据
		if(Tools.isNotNull(info.getAndroidId())) {
			//从快手库中查询对应的数据
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("androidId", info.getAndroidId()));
			//设置需要检测范围为2天内的
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY) - 72);
			pageModel.addQuery(Restrictions.gt("create_time", calendar.getTime()));
			pageModel.addQuery(Restrictions.eq("state", 0));
			List<KuaiShouAdDataEntity> list = kuaiShouAdDataContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				LOGGER.error("检测到本设备来自快手:"+info.getAndroidId());
				//检测对应的设备是否已经进入三方广告库
				ThirdPartySpreadEntity entity = thirdPartySpreadContract.findByProperty("clientId", info.getClientId());
				if(Tools.isNull(entity)) {
					//确认本次的设备激活属于三方快手来源  此处需要启动一个线程去做本任务
					String callback = list.get(0).getCallback();
					ExecutorServiceHelper.executor.execute(new KuaiShouDeviceThread(info.getAndroidId(), callback, info.getClientId(), header.getChannel()));
				}
			}
		}
		
		return ActionResult.success();
	}
	
	/**
	 * 新建一个设备
	 * @param info - AmassInfoDto
	 * @return PassDeviceDto
	 * @throws Exception
	 */
	private DeviceBO createDeviceInfo(AmassInfoDto info) throws Exception {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		HttpServletRequest request = RequestUtils.getCurrent().getRequest();
		
		DeviceCreateDto createDto = new DeviceCreateDto();
		createDto.setAppversion(header.getVersion());
		createDto.setBssid(info.getSsid());
		createDto.setChannel(header.getChannel());
		createDto.setCity_id(info.getCity());
		String areaName = ServiceHelper.getCityNameByIP(Tools.getIP(request));
		if(Tools.isNotNull(areaName)) {
			AreaDto cityArea = appAreaService.getCityByName(areaName);
			if(cityArea != null) {
				AreaDto[] areas = appAreaService.getAreas(cityArea.getId());
				createDto.setCountry_id(areas[0]!=null?areas[0].getId():0L);
				createDto.setProvince_id(areas[1]!=null?areas[1].getId():0L);
				createDto.setCity_id(areas[2]!=null?areas[2].getId():0L);
			}
		}else{
			String provinceName = ServiceHelper.getProvinceNameByIP(Tools.getIP(RequestUtils.getCurrent().getRequest()));
			if(Tools.isNotNull(provinceName)) {
				AreaDto provinceArea = appAreaService.getProvinceByName(provinceName);
				if(provinceArea != null) {
					AreaDto[] areas = appAreaService.getAreas(provinceArea.getId());
					createDto.setCountry_id(areas[0]!=null?areas[0].getId():0L);
					createDto.setProvince_id(areas[1]!=null?areas[1].getId():0L);
					createDto.setCity_id(areas[2]!=null?areas[2].getId():0L);
				}
			}
		}
		createDto.setClientid(header.getClientId());
		createDto.setCore(info.getCore());
		createDto.setCore_model(info.getCoreModel());
		createDto.setImei(info.getImei1());
		createDto.setImsi(info.getImsi1());
		createDto.setIp(Tools.getIP(request));
		createDto.setLat(info.getLat());
		createDto.setLng(info.getLng());
		createDto.setMac(info.getMac());
		createDto.setMobile(info.getMobile());
		createDto.setMobile_brand(info.getMobileBrand());
		createDto.setMobile_model(info.getMobileModel());
		createDto.setOs_ver(info.getReleaseVersion());
		createDto.setPkg_name(info.getPkgName());
		createDto.setPkgmd5(info.getPkgMd5());
		createDto.setPlatform(header.getOs_type());
		createDto.setRom_remain(info.getRomRemain());
		createDto.setRom_volume(info.getRomVolume());
		createDto.setScreensize(info.getScreenSize());
		createDto.setSdk_remain(info.getSdkRemain());
		createDto.setSdk_ver(info.getSdkVersion());
		createDto.setSdk_volume(info.getSdkVolume());
		createDto.setSsid(info.getSsid());
		createDto.setUserid(RequestUtils.getCurrent().getUserid());
		createDto.setVersioncode(header.getVersioncode());
		createDto.setNoticeClose(info.getNoticeClose());
		
		createDto.setAndroidId(info.getAndroidId());
		
		return deviceAgent.createDevice(createDto);
	}
	
	/**
	 * 更新设备信息
	 * @param info - 客户端传递过来的设备信息
	 * @param device - 设备信息
	 * @throws Exception
	 */
	private void updateDeviceInfo(AmassInfoDto info , DeviceBO device) throws Exception {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		HttpServletRequest request = RequestUtils.getCurrent().getRequest();
		
		long userid = RequestUtils.getCurrent().getUserid();
		
		DeviceModifyDto modifyDeviceDto = new DeviceModifyDto();
		modifyDeviceDto.setDeviceId(device.getDeviceId());
		if(userid != 0) {
			modifyDeviceDto.setUserid(userid);
		}
		if(Tools.isNotNull(info.getMobile())) {
			modifyDeviceDto.setMobile(info.getMobile());
		}
		if(info.getCity() != 0) {
			modifyDeviceDto.setCity_id(info.getCity());
		}
		if(info.getLng() != 0) {
			modifyDeviceDto.setLng(info.getLng());
		}
		if(info.getLat() != 0) {
			modifyDeviceDto.setLat(info.getLat());
		}
		if(Tools.isNotNull(info.getBssid())) {
			modifyDeviceDto.setBssid(info.getBssid());
		}
		if(Tools.isNotNull(info.getSsid())) {
			modifyDeviceDto.setSsid(info.getSsid());
		}
		if(Tools.isNotNull(header.getVersion())) {
			modifyDeviceDto.setAppversion(header.getVersion());
		}
		if(header.getVersioncode() != 0) {
			modifyDeviceDto.setVersioncode(header.getVersioncode());
		}
		if(info.getCore() != 0) {
			modifyDeviceDto.setCore(info.getCore());
		}
		if(Tools.isNotNull(info.getCoreModel())) {
			modifyDeviceDto.setCore_model(info.getCoreModel());
		}
		if(Tools.isNotNull(header.getChannel())) {
			modifyDeviceDto.setChannel(header.getChannel());
		}
		if(Tools.isNotNull(info.getReleaseVersion())) {
			modifyDeviceDto.setOs_ver(info.getReleaseVersion());
		}
		if(Tools.isNotNull(info.getSdkVersion())) {
			modifyDeviceDto.setSdk_ver(info.getSdkVersion());
		}
		if(Tools.isNotNull(info.getNoticeClose())) {
			modifyDeviceDto.setNoticeClose(info.getNoticeClose());
		}
		
		if(Tools.isNotNull(info.getAndroidId())) {
			modifyDeviceDto.setAndroidId(info.getAndroidId());
		}
		
		modifyDeviceDto.setIp(Tools.getIP(request));
		
		deviceAgent.modifyDevice(modifyDeviceDto);
	}

	private class KuaiShouDeviceThread implements Runnable {

		private String callback;
		
		private String androidId;
		
		private String clientid;
		
		private String channel;
		
		public KuaiShouDeviceThread(String androidId, String callback, String clientid, String channel) {
			this.androidId = androidId;
			this.callback= callback;
			this.clientid = clientid;
			this.channel = channel;
		}
		
		@Override
		public void run() {
			try {
				//进行与快手进行确认数据
				String url = callback+"&"+"event_type="+1+"&"+"event_time="+System.currentTimeMillis();
				LOGGER.error("进行确认回调的url:" + url + " androidid:" + androidId +" clientid:"+clientid +" channel:"+channel);
				//检测是否确认成功
				ResponseStatus status = HttpUtils.get(url);
				int suc = 0;
				if(Tools.isNotNull(status)) {
					String content = status.getContent();
					if(Tools.isNotNull(content)) {
						LOGGER.error("快手返回内容content:" + content);
						JSONObject json = JsonHelper.toJsonObject(content);
						if(Tools.isNotNull(json)) {
							int result = json.getIntValue("result");
							if(result == 1) {
								suc = 1;
							} 
						}
					}	
				}
				if(suc == 1) {
					//与快手进行数据确认成功
					ThirdPartySpreadEntity entity = new ThirdPartySpreadEntity();
					entity.setAndroidId(androidId);
					entity.setClientId(clientid);
					entity.setReg_channel(channel);
					entity.setOrigin(ThirdPartySpreadEnum.kuaishou.getCode());
					entity.setCreate_time(new Date());
					entity.setUpdate_time(new Date());
					thirdPartySpreadContract.insert(entity);
					
					//更新快手的数据表
					Map<String, Object> updateStatement = new HashMap<String, Object>();
					updateStatement.put("state", 1);
					kuaiShouAdDataContract.updateByProperty(updateStatement , "androidId", androidId);
				} else {
					//与快手进行数据确认失败
					LOGGER.error("访问快手回调接口出现了错误");
				}
			} catch (Exception e) {
				LOGGER.error("处理快手激活出现了问题:"+e);
			}
		}
	}
}

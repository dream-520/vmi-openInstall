package com.tigerjoys.shark.miai.agent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IDevicePriceAgent;
import com.tigerjoys.shark.miai.inter.contract.IDeviceContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomMobileContract;
import com.tigerjoys.shark.miai.inter.entity.DeviceEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomMobileEntity;

@Service
public class DevicePriceAgentImpl implements IDevicePriceAgent {

	@Autowired
	private IDeviceContract  deviceContract;
	
	@Autowired
	private IRechargeCustomCategoryContract rechargeCustomCategoryContract;
	
	@Autowired
	private IRechargeCustomMobileContract rechargeCustomMobileContract;
	
	@Override
	public boolean mobileModelMoreThanPrice(String clientId, int price) throws Exception {
		boolean condition = false;
		if(Tools.isNotNull(clientId)) {
			DeviceEntity device = deviceContract.findByProperty("clientid", clientId);
			if (Tools.isNotNull(device)) {
				RechargeCustomMobileEntity mobile = rechargeCustomMobileContract.findByProperty("name", device.getMobile_model());
				if(Tools.isNotNull(mobile)) {
					RechargeCustomCategoryEntity category = rechargeCustomCategoryContract.findById(mobile.getCategory_id());
					//检测是否满足对应的价格
					if (Tools.isNotNull(category) && price > 0 && category.getPrice() >= price) {
						condition = true;
					}
				} else {
					//特殊处理苹果手机
					if(Tools.isNotNull(device.getMobile_brand()) && device.getMobile_brand().equals("苹果"))
						condition = true;
				}
			}
		}
		return condition;
	}

}

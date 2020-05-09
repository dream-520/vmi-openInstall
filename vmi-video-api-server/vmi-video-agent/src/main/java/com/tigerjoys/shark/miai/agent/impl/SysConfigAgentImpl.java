package com.tigerjoys.shark.miai.agent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.ChargeDeductionConfigDto;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeConfigDto;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeRedFlowerConfigDto;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.VchatRoomObscurationConfigDto;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;

/**
 * 道具代理服务实现类
 * @author lipeng
 *
 */
@Service
public class SysConfigAgentImpl implements ISysConfigAgent {
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	/**
	 * 获得系统配置对象
	 * @param name - 配置名称
	 * @return SysConfigEntity
	 * @throws Exception
	 */
	public SysConfigEntity getSysConfig(String name) throws Exception {
		if(Tools.isNull(name)) {
			return null;
		}
		return sysConfigContract.findByProperty("name", name);
	}
	
	@Override
	public int getIntvalue(String name) throws Exception {
		return getIntValue(name, 0);
	}

	@Override
	public int getIntValue(String name, int defaultVal) throws Exception {
		SysConfigEntity sysConfig = getSysConfig(name);
		if(sysConfig == null) return defaultVal;
		
		return Tools.parseInt(sysConfig.getValue() , defaultVal);
	}

	@Override
	public void setIntValue(String name, int value) throws Exception {
		SysConfigEntity sysConfig = getSysConfig(name);
		if(sysConfig == null) {
			throw new NullPointerException();
		}
		
		SysConfigEntity temp = new SysConfigEntity();
		temp.setId(sysConfig.getId());
		temp.setValue(String.valueOf(value));
		sysConfigContract.update(temp);
	}

	@Override
	public int incrIntValue(String name, int v) throws Exception {
		SysConfigEntity sysConfig = getSysConfig(name);
		if(sysConfig == null) {
			throw new NullPointerException();
		}
		
		int val = Tools.parseInt(sysConfig.getValue() , 0);
		
		SysConfigEntity temp = new SysConfigEntity();
		temp.setId(sysConfig.getId());
		temp.setValue(String.valueOf(val + v));
		sysConfigContract.update(temp);
		
		return val + v;
	}

	@Override
	public JSONObject getJSONValue(String name) throws Exception {
		SysConfigEntity sysConfig = getSysConfig(name);
		if(sysConfig == null) return null;
		
		return JsonHelper.toJsonObject(sysConfig.getValue());
	}
	
	@Override
	public JSONObject getJSONValue(String name , boolean ordered) throws Exception {
		SysConfigEntity sysConfig = getSysConfig(name);
		if(sysConfig == null) return null;
		
		return JsonHelper.toJsonObject(sysConfig.getValue() , ordered);
	}

	@Override
	public <T> T getObject(String name, Class<T> clazz) throws Exception {
		SysConfigEntity sysConfig = getSysConfig(name);
		if(sysConfig == null) return null;
		
		return JsonHelper.toObject(sysConfig.getValue(), clazz);
	}

	@Override
	public VacuateConfigDto vacuate() throws Exception {
		VacuateConfigDto dto = getObject(Const.VACUATE_CTRL, VacuateConfigDto.class);
		return Tools.isNotNull(dto) ? dto : new VacuateConfigDto();
	}
	
	
	
	
	@Override
	public ChargeDeductionConfigDto chargeDeduction() throws Exception {
		ChargeDeductionConfigDto dto = getObject(Const.RECHARGE_DIAMONDS_INCOME_RATIO, ChargeDeductionConfigDto.class);
		return Tools.isNotNull(dto) ? dto : new ChargeDeductionConfigDto();
	}

	@Override
	public FirstChargeConfigDto firstCharge() throws Exception {
		FirstChargeConfigDto dto = getObject(Const.CUSTORM_CATEGORY_FIRSTCHARGE, FirstChargeConfigDto.class);
		return Tools.isNotNull(dto) ? dto : new FirstChargeConfigDto();
	}

	
	@Override
	public FirstChargeRedFlowerConfigDto firstChargeRedFlower() throws Exception {
		FirstChargeRedFlowerConfigDto dto = getObject(Const.REDFLOWER_FIRSTCHARGE, FirstChargeRedFlowerConfigDto.class);
		return Tools.isNotNull(dto) ? dto : new FirstChargeRedFlowerConfigDto();
	}
	
	
	@Override
	public VchatRoomObscurationConfigDto obscurationConfig() throws Exception {
		VchatRoomObscurationConfigDto dto = getObject(Const.VCHAT_ROOM_OBSCURATION, VchatRoomObscurationConfigDto.class);
		return Tools.isNotNull(dto) ? dto : new VchatRoomObscurationConfigDto();
	}
	
	
	@Override
	public int getVIPPopStatus() throws Exception {
		SysConfigEntity sysConfig = getSysConfig(Const.VIP_POP_STATUS);
		return Tools.isNull(sysConfig) ? 0 : Tools.intValue(sysConfig.getValue());
	}
 
	public boolean checkIncomeRadioNoPass(long price, double inc) throws Exception {
		boolean falg = false;
		ChargeDeductionConfigDto config = chargeDeduction();
		if (Tools.isNotNull(config)) {
			double deduction = price * config.getRatio();
			double incomeRadio = Tools.formatDouble2(deduction);
			if(incomeRadio<inc){
				return true;
			}
		}
		return falg;
	}
	
	public String replaceToN(String text){
		String ruleText = "";
		String[] textArray = Tools.split(text,"\\n");
		for(int i=0;i<textArray.length;i++){
			if(i == 0){
				ruleText =ruleText + textArray[i];
			}else{
				ruleText = ruleText+"\n"+textArray[i];
			}
		}
		return ruleText;
	}
	
	
	
	@Override
	public void clearCache() throws Exception {
		sysConfigContract.clearAllCache();
	}

}

package com.tigerjoys.shark.miai.agent;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.shark.miai.agent.dto.ChargeDeductionConfigDto;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeConfigDto;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeRedFlowerConfigDto;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.VchatRoomObscurationConfigDto;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;

/**
 * 系统配置服务代理接口
 * @author lipeng
 *
 */
public interface ISysConfigAgent {
	
	/**
	 * 获得系统配置对象
	 * @param name - 配置名称
	 * @return SysConfigEntity
	 * @throws Exception
	 */
	public SysConfigEntity getSysConfig(String name) throws Exception ;
	/**
	 * 获得Int类型
	 * @param name - 配置名称
	 * @return int
	 * @throws Exception
	 */
	public int getIntvalue(String name) throws Exception;
	
	/**
	 * 获得Int类型
	 * @param name - 配置名称
	 * @param defaultVal - 默认返回值
	 * @return int
	 * @throws Exception
	 */
	public int getIntValue(String name , int defaultVal) throws Exception;
	
	/**
	 * 设置int类型
	 * @param name - 配置名称
	 * @param value - 设置的值
	 * @throws Exception
	 */
	public void setIntValue(String name , int value) throws Exception;
	
	/**
	 * 设置int自动增长的值
	 * @param name - 配置名称
	 * @param v - 设置的值
	 * @return int
	 * @throws Exception
	 */
	public int incrIntValue(String name , int v) throws Exception;
	
	/**
	 * 返回JSON数据类型
	 * @param name - 配置名称
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getJSONValue(String name) throws Exception;
	
	/**
	 * 返回JSON数据类型
	 * @param name - 配置名称
	 * @param ordered - 是否按照文本顺序显示
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getJSONValue(String name , boolean ordered) throws Exception;
	
	/**
	 * 将Json数据返回具体的实体对象
	 * @param name - 配置名称
	 * @param clazz - clazz
	 * @return T
	 * @throws Exception
	 */
	public <T> T getObject(String name , Class<T> clazz) throws Exception;
	
	/**
	 * 分成控制配置
	 * @return
	 * @throws Exception 
	 */
	public VacuateConfigDto vacuate() throws Exception;
	
	
	/**
	 * 充值抵扣配置
	 * @return
	 * @throws Exception 
	 */
	public ChargeDeductionConfigDto chargeDeduction() throws Exception;
	
	
	/**
	 * 蒙层配置
	 * @return
	 * @throws Exception 
	 */
	public VchatRoomObscurationConfigDto obscurationConfig() throws Exception;
	
	/**
	 * 首充配值
	 * @return
	 * @throws Exception
	 */
	public FirstChargeConfigDto firstCharge() throws Exception;
	
	/**
	 * 小红花首充配值
	 * @return
	 * @throws Exception
	 */
	public FirstChargeRedFlowerConfigDto firstChargeRedFlower() throws Exception;
	
	/**
	 * 用户抵扣收益检查，不通过返回  true 
	 * @param price   价格 分
	 * @param inc    收益  分
	 * @return
	 * @throws Exception
	 */
	public boolean checkIncomeRadioNoPass(long price, double inc) throws Exception;
	
	/**
	 * 查看VIP弹窗状态
	 * @return
	 * @throws Exception
	 */
	public int getVIPPopStatus() throws Exception ;
	
	/**
	 * 清理缓存信息
	 * @throws Exception
	 */
	public void clearCache() throws Exception;
	
	/**
	 * 替换字符 \\n 成  \n
	 * @param text
	 * @return
	 */
	public String replaceToN(String text);
	
}

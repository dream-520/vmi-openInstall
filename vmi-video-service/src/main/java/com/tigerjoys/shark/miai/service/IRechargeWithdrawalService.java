package com.tigerjoys.shark.miai.service;

import java.util.Map;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeRedFlowerConfigDto;
import com.tigerjoys.shark.miai.agent.enums.RechargeCategoryPriceEnum;
import com.tigerjoys.shark.miai.dto.service.RechargePriceDto;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargePriceEntity;

/** 
 * ClassName: IRechargeWithdrawalService <br/> 
 * date: 2017年8月14日 下午8:46:03 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public interface IRechargeWithdrawalService {
	
	/**
	 * 获取原生充值列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActionResult getNativePriceList() throws Exception;

	/**
	 * 获取充值列表
	 * @param type  0 H5价格列表  1 原生价格列表
	 * @param priceType  类型表{@link RechargeCategoryPriceEnum}
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPriceList(int type,int priceType) throws Exception;
	
	
	
	/**
	 * 充值钻石
	 * 
	 * @param priceId
	 * @param channelCode
	 * @param money (元)
	 * @param income - 花费收益
	 * @param receipt
	 * @return
	 * @throws Exception
	 */
	public ActionResult recharge(long priceId, int channelCode, double money, double income,  Map<String,String> ipaParam) throws Exception;
	
	/**
	 * 处理H5支付请求
	 * @param priceId
	 * @param channelCode
	 * @param money
	 * @param income
	 * @param receipt
	 * @return
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public ActionResult rechargeH5(long priceId, int channelCode, double money, double income, String receipt) throws Exception;
	
	/**
	 * 收益提现
	 * @return
	 * @throws Exception
	 */
	public ActionResult getIncomePage() throws Exception;
	
	/**
	 * 奖励金
	 * @return
	 * @throws Exception
	 */
	public ActionResult getBonusPage() throws Exception;
	
	/**
	 * 提现记录(分页)
	 * @return
	 * @throws Exception
	 */
	public ActionResult withdrawalCashLog() throws Exception;
	
	/**
	 * 收益明细(分页)
	 * @return
	 * @throws Exception
	 */
	public ActionResult incomeCashLog(Integer stamp) throws Exception;

	public ActionResult getEnergyPriceList() throws Exception;

	public ActionResult rechargeEnergy(long priceId, int channelCode, double money, double income, String receipt) throws Exception;
	
	public RechargePriceEntity getRechargeCustomPrice(Long priceId) throws Exception;
	
	/**
	 * 获取手机型号的类别
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public RechargeCustomCategoryEntity getRechargeCustomCategory(String model) throws Exception;
	
	/**
	 * 小红花按类型首充
	 * @return
	 * @throws Exception
	 */
	public FirstChargeRedFlowerConfigDto getFirstRedFlowerCategoryCharge() throws Exception;
	
	/**
	 * 根据priceId获取价格
	 * @param category
	 * @param priceId
	 * @return
	 * @throws Exception
	 */
	public RechargePriceDto getRechargePriceByPriceId(int osType,long category ,Long priceId) throws Exception;
	
	/**
	 * 限制充值列表
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean checkChargeLimit200(long userid) throws Exception;
	
	/**
	 * 根据用户安状列表限制充值列表
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean checkUserAPPLimit(long userid) throws Exception;
	
	/** 
	 * 用户 ID,设备 ,IP 限制
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean checkUserAccountLimit(long userid) throws Exception;
	
	/**
	 * 只要充过100元，以后最少就是100元
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean checkChargeLimit(long userid) throws Exception;
	
	
}

/**
 * 
 */
package com.tigerjoys.shark.miai.service;

import java.util.Map;

import com.tigerjoys.nbs.common.ActionResult;

/** 
 * ClassName: IRechargeWithdrawalService <br/> 
 * date: 2017年8月14日 下午8:46:03 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public interface IRechargeRedFlowerService {
	
	/**
	 * 获取原生充值列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActionResult getNativePriceList() throws Exception;

	/**
	 * 获取充值列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPriceList() throws Exception;
	
	
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
	public ActionResult recharge(long priceId, int channelCode, double money, double income,Map<String,String> ipaParam) throws Exception;
	
	
}

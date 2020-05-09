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
public interface IRechargeGuardVipService {
	
	/**
	 * 获取原生充值列表
	 * @param anchorId  主播ID
	 * @param type  1 守护  2 VIP
	 * @return
	 * @throws Exception
	 */
	public ActionResult getNativePriceList(long anchorId,int type) throws Exception;

	/**
	 * 获取充值列表
	 * @param type  1 守护  2 VIP
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPriceList(long anchorId,int type) throws Exception;
	
	/**
	 * 购买周卡列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getWeeksCardPriceList() throws Exception;
	
	
	/**
	 * 获取VIP充值列表
	 * @param mobileModel  手机型号
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getVipPriceList(String mobileModel) throws Exception;
	
	/**
	 * 充值订单生成
	 * 
	 * @param priceId
	 * @param channelCode
	 * @param money (元)
	 * @param income - 花费收益
	 * @param receipt
	 * @return
	 * @throws Exception
	 */
	public ActionResult recharge(long priceId, int channelCode, double money, double income, Map<String,String> ipaParam,Long anchorId) throws Exception;
	
	
}

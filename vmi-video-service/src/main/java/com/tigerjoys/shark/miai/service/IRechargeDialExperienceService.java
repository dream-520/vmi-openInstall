/**
 * 
 */
package com.tigerjoys.shark.miai.service;

import java.util.Map;

import org.springframework.ui.Model;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.RechargePriceDto;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomPriceEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargePriceEntity;

/** 
 * ClassName: IRechargeWithdrawalService <br/> 
 * date: 2017年8月14日 下午8:46:03 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public interface IRechargeDialExperienceService {
	

	/**
	 * 获取充值列表
	 * @param type  1 守护  2 VIP
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPriceList() throws Exception;
	
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
	public ActionResult recharge(long priceId, int channelCode, double money, double income, Map<String,String> ipaParam) throws Exception;
	
	
	/**
	 * 查看体验价格
	 * @param userId
	 * @param userBo
	 * @return
	 */
	public long getPrice(long userId,UserBO userBo) throws Exception;
	
}

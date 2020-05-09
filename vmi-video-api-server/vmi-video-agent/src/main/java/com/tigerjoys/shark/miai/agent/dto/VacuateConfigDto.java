/**
 *
 */
package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

import com.tigerjoys.nbs.common.utils.JsonHelper;

/**
 * ClassName: VacuateConfigDto <br/>
 * date: 2017年8月17日 下午4:29:46 <br/>
 *
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public class VacuateConfigDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1921933089263806157L;

	/**
	 * 钻兑钱(元)倍率-小数
	 */
	private Float diamondToMoney = 1F;
	
	
	/**
	 * 小红花转钱(元)倍率-小数
	 */
	private Float flowerToMoney = 0.2F;
	/**
	 * 扣税比例（%）
	 */
	private Float taxRatio = 20F;
	/**
	 * 服务方分成比例（%）
	 */
	private Float serviceRatio = 70F;
	/**
	 * 代理方/邀请者分成比例（%）
	 */
	private Float proxyRatio = 10F;
	/**
	 * 任务达人约额外分成比例（%）
	 */
	private Float taskRatio = 0F;

	/**
	 * 充值邀请者收益
	 */
	private Float rechargeIncome = 10F;


	public Float getDiamondToMoney() {
		return diamondToMoney;
	}

	public void setDiamondToMoney(Float diamondToMoney) {
		this.diamondToMoney = diamondToMoney;
	}

	public Float getTaxRatio() {
		return taxRatio;
	}

	public void setTaxRatio(Float taxRatio) {
		this.taxRatio = taxRatio;
	}

	public Float getServiceRatio() {
		return serviceRatio;
	}

	public void setServiceRatio(Float serviceRatio) {
		this.serviceRatio = serviceRatio;
	}

	public Float getProxyRatio() {
		return proxyRatio;
	}

	public void setProxyRatio(Float proxyRatio) {
		this.proxyRatio = proxyRatio;
	}

	public Float getTaskRatio() {
		return taskRatio;
	}

	public void setTaskRatio(Float taskRatio) {
		this.taskRatio = taskRatio;
	}

	public Float getRechargeIncome() {
		return rechargeIncome;
	}

	public void setRechargeIncome(Float rechargeIncome) {
		this.rechargeIncome = rechargeIncome;
	}
	
	

	public Float getFlowerToMoney() {
		return flowerToMoney;
	}

	public void setFlowerToMoney(Float flowerToMoney) {
		this.flowerToMoney = flowerToMoney;
	}

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}

package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.List;

public class PurchaseCreditScorePageDto implements Serializable {

	private static final long serialVersionUID = 781154031193614042L;
	
	/**
	 * 信用账户信用分余额
	 */
	private String balanceScore;
	
	/**
	 * 信用账户信用分余额
	 */
	private String balanceDiamonds;
	
	/**
	 * 后台配置信用分购买方式
	 */
	private List<CreditScoreConfigureBO> configures;

	public String getBalanceScore() {
		return balanceScore;
	}

	public void setBalanceScore(String balanceScore) {
		this.balanceScore = balanceScore;
	}

	public List<CreditScoreConfigureBO> getConfigures() {
		return configures;
	}

	public void setConfigures(List<CreditScoreConfigureBO> configures) {
		this.configures = configures;
	}

	public String getBalanceDiamonds() {
		return balanceDiamonds;
	}

	public void setBalanceDiamonds(String balanceDiamonds) {
		this.balanceDiamonds = balanceDiamonds;
	}
	
}

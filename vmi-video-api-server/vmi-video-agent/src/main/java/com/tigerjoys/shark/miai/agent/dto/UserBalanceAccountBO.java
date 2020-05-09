package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * 用户现金账户信息
 * @author chengang
 *
 */
public class UserBalanceAccountBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1460026629423934676L;
	
	/**
	 * 账户ID
	 */
	private long accountId;
	
	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 用户总获取金额，单位 分
	 */
	private long deposit;
	
	/**
	 * 用户总消费金额，单位 分
	 */
	private long consume;
	
	/**
	 * 用户当前余额，单位 分
	 */
	private long balance;
	
	/**
	 * 账户状态,1正常,-1冻结
	 */
	private int status;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getDeposit() {
		return deposit;
	}

	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}

	public long getConsume() {
		return consume;
	}

	public void setConsume(long consume) {
		this.consume = consume;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}

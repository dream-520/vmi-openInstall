package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

public class PayPrepayResultDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -17091131031249973L;
	
	/**
	 * 返回信息
	 */
	private String data;
	
	/**
	 * 支付记录ID
	 */
	private long pay_action_id;
	
	public PayPrepayResultDto() {
		
	}
	
	public PayPrepayResultDto(String data , long pay_action_id) {
		this.data = data;
		this.pay_action_id = pay_action_id;
	}
	
	public static PayPrepayResultDto create(String data , long pay_action_id) {
		return new PayPrepayResultDto(data, pay_action_id);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getPay_action_id() {
		return pay_action_id;
	}

	public void setPay_action_id(long pay_action_id) {
		this.pay_action_id = pay_action_id;
	}

}

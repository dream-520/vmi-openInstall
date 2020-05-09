/**
 *
 */
package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

import com.tigerjoys.nbs.common.utils.JsonHelper;

/**
 * ClassName: FirstChargeConfigDto <br/>
 *
 * @author yangjunming
 * @version
 * @since JDK 1.8.0
 */
public class FirstChargeRedFlowerConfigDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1921933089263806157L;

	/**
	 * 标题
	 */
	private String title = "100朵";
	
	
	/**
	 * 描述
	 */
	private String description ;
	
	
	/**
	 * 金额
	 */
	private Double money = 20.0;
	
	
	/**
	 * 钻石
	 */
	private Integer flower = 100 ;
	
	
	/**
	 * 状态  0 不开启  1 开启
	 */
	private int status = 0 ;
	
	

	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Double getMoney() {
		return money;
	}



	public void setMoney(Double money) {
		this.money = money;
	}


	public Integer getFlower() {
		return flower;
	}

	public void setFlower(Integer flower) {
		this.flower = flower;
	}

	
	
	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}

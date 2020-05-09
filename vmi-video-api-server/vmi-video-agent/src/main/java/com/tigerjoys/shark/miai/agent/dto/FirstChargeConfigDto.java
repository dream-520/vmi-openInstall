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
public class FirstChargeConfigDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1921933089263806157L;

	/**
	 * 标题
	 */
	private String title = "48钻";
	
	
	/**
	 * 描述
	 */
	private String description ;
	
	
	/**
	 * 金额
	 */
	private Double money = 48.0;
	
	
	/**
	 * 钻石
	 */
	private Integer diamond = 48 ;
	
	

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



	public Integer getDiamond() {
		return diamond;
	}



	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}



	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}

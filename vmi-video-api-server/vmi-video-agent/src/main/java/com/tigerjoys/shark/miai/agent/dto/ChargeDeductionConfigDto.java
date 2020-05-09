/**
 *
 */
package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

/**
 * ClassName: VacuateConfigDto <br/>
 * date: 2017年8月17日 下午4:29:46 <br/>
 *
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public class ChargeDeductionConfigDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1921933089263806157L;

	/**
	 * 充值抵扣开关
	 */
	private int status = 0;
	/**
	 * 扣税比例（%）
	 */
	private float ratio =0.0F ;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	
}
